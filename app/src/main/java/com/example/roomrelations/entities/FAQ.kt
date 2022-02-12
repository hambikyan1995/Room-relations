package com.example.roomrelations.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FAQ(
        @PrimaryKey(autoGenerate = false)
        val id: Int,
        val name: String,
        val chatName: String
)
