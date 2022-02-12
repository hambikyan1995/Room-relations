package com.example.roomrelations.relations.nestedrelationships

import androidx.room.Entity

@Entity(primaryKeys = ["userName", "chatName","role"])
data class UserChatRoleCrossRef(
        val userName: String,
        val chatName: String,
        val role: String)