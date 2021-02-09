package com.storage.system

import com.storage.system.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class SystemApplication

fun main(args: Array<String>) {
    runApplication<SystemApplication>(*args)
}
