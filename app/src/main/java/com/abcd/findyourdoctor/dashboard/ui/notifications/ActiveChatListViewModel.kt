package com.abcd.findyourdoctor.dashboard.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abcd.findyourdoctor.util.SharedPreferenceUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class ActiveChatListViewModel(application: Application) : AndroidViewModel(application) {

    private val activeChatList: ArrayList<ActiveChatData> = ArrayList()
    private val database = FirebaseDatabase.getInstance().reference
    val activeChatLiveData: MutableLiveData<ArrayList<ActiveChatData>> = MutableLiveData()

    fun getActiveChatList() {
        val userId = SharedPreferenceUtil.getPreferences(getApplication(), "userId", "")
        database.child("activeChats").child(userId.toString()).orderByChild("timestamp").addValueEventListener(object : ValueEventListener {
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
                TODO("Not yet implemented")
            }

        })
    }
}