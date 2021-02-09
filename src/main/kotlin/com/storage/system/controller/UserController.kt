package com.storage.system.controller

import com.storage.system.kafka.KafkaProducer
import com.storage.system.model.User
import com.storage.system.model.UserResponse
import com.storage.system.repository.UserRepository
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger { }

@RestController
class UserController(
    private val producer: KafkaProducer,
    private val userRepository: UserRepository
) {

    @GetMapping("/users")
    fun getUsers(@RequestParam(value = "name", required = false) name: String?): ResponseEntity<List<UserResponse>> {
        val users: List<User> =
            name?.takeIf { it.isNotEmpty() }?.let { userName ->
                userRepository.findUsersByName(userName)
            } ?: userRepository.findAll()

        val usersView = users.map { user -> user.toUserView() }

        logger.info { "Users are $usersView" }

        return if (usersView.isNotEmpty())
            ResponseEntity.ok(usersView)
        else
            ResponseEntity.notFound().build()
    }

    @GetMapping("user/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<UserResponse> {

        val userById = userRepository.findUserById(id).toUserView()

        logger.info { "User id: $id is $userById" }

        return ResponseEntity.ok(userById)

    }

    @PostMapping("/user" )
    fun addUser(@RequestBody user: User): ResponseEntity<String> {

        producer.sendToKafka(user)

        logger.info { "Adding user $user" }

        return ResponseEntity.ok("User $user published")

    }

    fun User.toUserView() = UserResponse(
        id = id,
        name = name,
        email = email
    )

}