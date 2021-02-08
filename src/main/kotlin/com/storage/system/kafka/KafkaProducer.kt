package com.storage.system.kafka

import com.storage.system.model.User
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger { }
private const val USERS_SYSTEM: String = "system-topic"

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, User>
) {

    fun sendToKafka(user: User){

        kafkaTemplate.send(USERS_SYSTEM, user)

        logger.info { "Message is being produced: $user" }
    }
}