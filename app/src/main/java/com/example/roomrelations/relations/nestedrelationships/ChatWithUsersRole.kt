package com.example.roomrelations.relations.nestedrelationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.UserEntity

data class ChatWithUsersRole(
        @Embedded
        val chat: ChatEntity,

        @Relation(
            parentColumn = "chatName",
            entityColumn = "userName",
            entity = UserEntity::class,
            associateBy = Junction(UserChatRoleCrossRef::class),
        )
        val users: List<UserWithRole>?,
)
