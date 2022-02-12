package com.example.roomrelations.relations.onetoone

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.FAQ

data class FAQWithChats(
        @Embedded
        val faq: FAQ,
        @Relation(
            parentColumn = "chatName",
            entityColumn = "chatName"
        )
        val chat: ChatEntity?
)