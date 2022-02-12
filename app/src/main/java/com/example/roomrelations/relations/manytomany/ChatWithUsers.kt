package com.example.roomrelations.relations.manytomany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.UserEntity

data class ChatWithUsers(
        @Embedded
        val chat: ChatEntity,

        @Relation(
            parentColumn = "chatName",
            entityColumn = "userName",
            associateBy = Junction(UserChatCrossRef::class)
        )
        val users: List<UserEntity>?,
)
