package com.example.roomrelations.relations.onetomany

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomrelations.entities.FAQ
import com.example.roomrelations.entities.ChatEntity


data class ChatWithFAQs(
        @Embedded
        val chat: ChatEntity,
        @Relation(
            parentColumn = "chatName",
            entityColumn = "chatName"
        )
        val faqs: List<FAQ>?
)