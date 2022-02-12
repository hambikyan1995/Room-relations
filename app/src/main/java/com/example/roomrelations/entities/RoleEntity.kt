package com.example.roomrelations.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoleEntity(
        @PrimaryKey(autoGenerate = false)
        val role: String
)