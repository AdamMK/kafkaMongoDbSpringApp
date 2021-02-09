package com.storage.system.repository

import com.storage.system.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, String> {
    fun findUserById(id: Int): User?

    fun findUsersByName(name: String?): List<User>

    fun findByEmail(email: String): User

}