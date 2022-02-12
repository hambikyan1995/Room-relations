package com.example.roomrelations

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.roomrelations.entities.ChatEntity
import com.example.roomrelations.entities.FAQ
import com.example.roomrelations.entities.RoleEntity
import com.example.roomrelations.entities.UserEntity
import com.example.roomrelations.relations.manytomany.UserChatCrossRef
import com.example.roomrelations.relations.nestedrelationships.UserChatRoleCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val conversationsDao by lazy { MyDatabase.getInstance(this).conversationsDao }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertJob = insertData()

        insertJob.invokeOnCompletion {
            lifecycleScope.launch {
                /** One to one relationships*/
                val chatFaqsWithChats = conversationsDao.getFAQAndChat(1)
                Log.i("database_log", "--- One to one ---\n FAQ name -> " + chatFaqsWithChats.faq.name +
                        ", Chat name -> ${chatFaqsWithChats.chat?.chatName}}")

                /** One to many relationships*/
                val chatNewsWithFaqs = conversationsDao.getChatWithFAQs("news")
                Log.i("database_log", "--- One to many --- \nChat name -> " + chatNewsWithFaqs.chat.chatName +
                        ", FAQs -> ${chatNewsWithFaqs.faqs?.map { it.name }}")

                /** Many to many relationships*/
                val chatWithUsers = conversationsDao.getChatWithUsers("news")
                Log.i("database_log", "--- Many to many --- \nChat name -> " + chatWithUsers.chat.chatName +
                        ", Users -> ${chatWithUsers.users?.map { it.userName }}")

                val userWithChats = conversationsDao.getUserWithChats("Artur")
                Log.i("database_log", "User name -> " + userWithChats.user.userName +
                        ", Chats -> ${userWithChats.chats?.map { it.chatName }}")

                /** Nested relationships*/
                val chatWithUserAmdRole = conversationsDao.getChatWithUserAndRole("weather")
                Log.i("database_log", "--- Nested relationships --- \nChat name -> " + "${chatWithUserAmdRole.chat.chatName}," +
                        " Users with role -> ${
                            chatWithUserAmdRole.users?.map {
                                "User name -> " +
                                        "${it.user.userName}, " + "Role -> ${it.role?.role}"
                            }}")
            }
        }
    }

    private fun insertData(): Job {
        val listChats = arrayListOf(
            ChatEntity("news"),
            ChatEntity("weather"),
            ChatEntity("sport")
        )

        val listChatFAQs = arrayListOf(
            FAQ(id = 1, name = "How many members can be in the news chat?", chatName = "news"),
            FAQ(id = 2, name = "Who created the sport chat?", chatName = "news"),
        )

        val listUsers = arrayListOf(
            UserEntity("Artur"),
            UserEntity("Vardan"),
            UserEntity("Karen")
        )

        val listRoles = arrayListOf(
            RoleEntity("owner"),
            RoleEntity("member"),
            RoleEntity("admin"),
            RoleEntity("editor")
        )

        return lifecycleScope.launch(Dispatchers.IO) {
            conversationsDao.insertChat(listChats)
            conversationsDao.insertChatFAQs(listChatFAQs)
            conversationsDao.insertUser(listUsers)
            conversationsDao.insertRoles(listRoles)

            /**from many yo many relationship used @UserChatCrossRef*/
            conversationsDao.insertUserChatCrossRef(listOf(
                UserChatCrossRef("Artur", "news"),
                UserChatCrossRef("Artur", "sport"),
                UserChatCrossRef("Karen", "news")
            ))

            /**from nested relationships used @UserChatRoleCrossRef*/
            conversationsDao.insertChatWithRole(listOf(
                UserChatRoleCrossRef("Artur", "news", "owner"),
                UserChatRoleCrossRef("Vardan", "news", "member"),
                UserChatRoleCrossRef("Karen", "news", "owner"),
                UserChatRoleCrossRef("Karen", "weather", "member"),
                UserChatRoleCrossRef("Artur", "weather", "owner"),
                UserChatRoleCrossRef("Karen", "sport", "admin"),
                UserChatRoleCrossRef("Vardan", "sport", "editor")))
        }
    }
}