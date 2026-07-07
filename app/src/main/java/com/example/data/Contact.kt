package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey val publicKey: String,
    val name: String,
    val addresses: String, // Stored as JSON string list
    val blocked: Boolean = false,
    val isOnline: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
