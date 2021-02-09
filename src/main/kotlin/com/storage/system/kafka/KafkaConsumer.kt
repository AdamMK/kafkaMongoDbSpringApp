package com.storage.system.kafka

import com.storage.system.model.User
import com.storage.system.repository.UserRepository
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class KafkaConsumer(val userRepository: UserRepository) {

    @KafkaListener(topics = ["system-topic"])
    fun consumeUser(record: ConsumerRecord<String, User>){

      logger.info { "Message consumed with value: ${record.value()} and key: ${record.key()}" }

        userRepository.save(record.value())
    }
}