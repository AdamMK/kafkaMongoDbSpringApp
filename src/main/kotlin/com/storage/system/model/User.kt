package com.storage.system.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(
    @Id
    val userId: Int,
    val userName: String,
    val userEmail: String
)