package com.example.roomrelations.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ChatEntity(
        @PrimaryKey(autoGenerate = false)
        val chatName: String
)