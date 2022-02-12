package com.example.roomrelations.relations.manytomany

import androidx.room.Entity

@Entity(primaryKeys = ["userName", "chatName"])
data class UserChatCrossRef(
        val userName: String,
        val chatName: String)