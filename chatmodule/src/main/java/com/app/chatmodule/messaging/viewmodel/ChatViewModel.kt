package com.app.chatmodule.messaging.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.app.chatmodule.messaging.ChatConstant
import com.app.chatmodule.messaging.entity.ActiveChatData
import com.app.chatmodule.messaging.entity.ChatData
import com.app.chatmodule.messaging.entity.SecondUserData
import com.app.chatmodule.util.SharedPreferenceUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.NoSuchElementException

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val database = FirebaseDatabase.getInstance().reference
    private var chatId: String = ""
    private var userId: String =
        SharedPreferenceUtil.getPreferences(application, ChatConstant.USER_ID, "");

    fun updateActiveChats(chatId: String, secondUserData: SecondUserData) {
        this.chatId = chatId
        database.child(ChatConstant.MESSAGES).child(chatId).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children
                try {
                    val messageObj = data.last().getValue(ChatData::class.java)
                    if (messageObj != null) {
                        Handler(Looper.getMainLooper()).post(Runnable {
                            updateActiveUsers(messageObj, secondUserData)
                        })

                    }
                } catch (ex: NoSuchElementException) {
                    Log.e("FirebaseChat", ex.localizedMessage)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseChat", error.message)
            }

        })
    }

    private fun updateActiveUsers(messageObj: ChatData, secondUserData: SecondUserData) {
        //Second user's data visible to primary user
        val chatDataUser1 = ActiveChatData(
            message = messageObj.message,
            timestamp = messageObj.timestamp,
            userId = secondUserData.id!!,
            imageUrl = "",
            name = secondUserData.name!!
        )
        //Primary user's data visible to secondary user
        val chatDataUser2 = ActiveChatData(
            message = messageObj.message,
            timestamp = messageObj.timestamp,
            userId = userId,
            imageUrl = "",
            name = getUserName()
        )
        database.child(ChatConstant.ACTIVE_CHATS).child(userId).child(chatId)
            .setValue(chatDataUser1)
        database.child(ChatConstant.ACTIVE_CHATS).child(secondUserData.id!!).child(chatId)
            .setValue(chatDataUser2)
    }

    fun getUserName(): String {
        if (!TextUtils.isEmpty(
                SharedPreferenceUtil.getPreferences(
                    getApplication(),
                    ChatConstant.USER_NAME,
                    ""
                )
            )
        ) {
            return SharedPreferenceUtil.getPreferences(getApplication(), ChatConstant.USER_NAME, "")
        } else if (!TextUtils.isEmpty(
                SharedPreferenceUtil.getPreferences(
                    getApplication(),
                    ChatConstant.USER_PHONE,
                    ""
                )
            )
        ) {
            return SharedPreferenceUtil.getPreferences(
                getApplication(),
                ChatConstant.USER_PHONE,
                ""
            )
        }

        return "FYD User"
    }
}