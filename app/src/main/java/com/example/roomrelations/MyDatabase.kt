package com.example.roomrelations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.FAQ
import com.example.roomrelations.entities.RoleEntity
import com.example.roomrelations.entities.UserEntity
import com.example.roomrelations.relations.manytomany.UserChatCrossRef
import com.example.roomrelations.relations.nestedrelationships.UserChatRoleCrossRef

@Database(
    entities = [
        UserEntity::class, ChatEntity::class, UserChatRoleCrossRef::class, UserChatCrossRef::class,
        RoleEntity::class, FAQ::class
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract val conversationsDao: ConversationsDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}