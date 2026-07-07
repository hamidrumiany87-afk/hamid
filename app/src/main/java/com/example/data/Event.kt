package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val publicKey: String,
    val address: String,
    val type: String, // e.g. INCOMING_ACCEPTED, OUTGOING_MISSED
    val date: Long = System.currentTimeMillis()
)
