package com.example.roomrelations.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity(
        @PrimaryKey(autoGenerate = false)
        val userName: String
)