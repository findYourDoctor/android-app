package com.app.chatmodule.messaging.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.app.chatmodule.messaging.ChatConstant
import com.app.chatmodule.messaging.entity.ActiveChatData
import com.app.chatmodule.util.SharedPreferenceUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ActiveChatListViewModel(application: Application) : AndroidViewModel(application) {

    private val activeChatList: ArrayList<ActiveChatData> = ArrayList()
    private val database = FirebaseDatabase.getInstance().reference
    val activeChatLiveData: MutableLiveData<ArrayList<ActiveChatData>> = MutableLiveData()

    fun getActiveChatList() {
        val userId = SharedPreferenceUtil.getPreferences(getApplication(), ChatConstant.USER_ID, "")
        database.child(ChatConstant.ACTIVE_CHATS).child(userId.toString()).orderByChild("timestamp").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                activeChatList.clear()
                for (user in snapshot.children) {
                    val data = user.getValue(ActiveChatData::class.java)
                    data?.let { activeChatList.add(it) }
                }

                activeChatLiveData.apply {
                    activeChatList.reverse()
                    value = activeChatList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseChat", error.message)
            }

        })
    }
}