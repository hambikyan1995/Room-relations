package com.example.roomrelations

import androidx.room.*
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.FAQ
import com.example.roomrelations.entities.RoleEntity
import com.example.roomrelations.entities.UserEntity
import com.example.roomrelations.relations.nestedrelationships.ChatWithUsersRole
import com.example.roomrelations.relations.manytomany.ChatWithUsers
import com.example.roomrelations.relations.manytomany.UserWithChats
import com.example.roomrelations.relations.onetoone.FAQWithChats
import com.example.roomrelations.relations.manytomany.UserChatCrossRef
import com.example.roomrelations.relations.nestedrelationships.UserChatRoleCrossRef
import com.example.roomrelations.relations.onetomany.ChatWithFAQs

@Dao
interface ConversationsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: List<ChatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoles(listRoles: ArrayList<RoleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserChatCrossRef(listOf: List<UserChatCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatWithRole(chat: List<UserChatRoleCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatFAQs(listChatFAQ: ArrayList<FAQ>)

    @Transaction
    @Query("select * from ChatEntity where chatName=:chatName")
    suspend fun getChatWithFAQs(chatName: String): ChatWithFAQs

    @Transaction
    @Query("select * from FAQ where id= :faqId")
    suspend fun getFAQAndChat(faqId: Int): FAQWithChats

    @Transaction
    @Query("select * from ChatEntity where chatName=:chatName")
    suspend fun getChatWithUsers(chatName: String): ChatWithUsers

    @Transaction
    @Query("select * from UserEntity where userName=:userName")
    suspend fun getUserWithChats(userName: String): UserWithChats

    @Transaction
    @Query("select * from ChatEntity where chatName=:chatName")
    suspend fun getChatWithUserAndRole(chatName: String): ChatWithUsersRole

}