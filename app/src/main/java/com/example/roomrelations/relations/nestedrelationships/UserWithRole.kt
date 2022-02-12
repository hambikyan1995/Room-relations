package com.example.roomrelations.relations.nestedrelationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.roomrelations.entities.RoleEntity
import com.example.roomrelations.entities.UserEntity

data class UserWithRole(
        @Embedded
        val user: UserEntity,
        @Relation(
            parentColumn = "userName",
            entityColumn = "role",
            associateBy = Junction(UserChatRoleCrossRef::class)
        )
        val role: RoleEntity?,
)
