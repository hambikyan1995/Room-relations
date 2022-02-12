package com.example.roomrelations.relations.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.UserEntity

data class UserWithChats(
        @Embedded
        val user: UserEntity,

        @Relation(
            parentColumn = "userName",
            entityColumn = "chatName",
            associateBy = Junction(UserChatCrossRef::class))
        val chats: List<ChatEntity>?,
)
