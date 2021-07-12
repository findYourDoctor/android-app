package com.abcd.findyourdoctor.dashboard.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class ActiveChatListViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getActiveChatList(activeChatData : ActiveChatData) {
        database.child("activeChat").child("chatData").push().setValue(activeChatData)
    }
}