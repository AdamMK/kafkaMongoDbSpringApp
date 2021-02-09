package com.storage.system.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(
    @Id
    val id: Int,
    val name: String,
    val email: String
)