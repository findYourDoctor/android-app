package com.abcd.findyourdoctor.messaging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abcd.findyourdoctor.R

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance())
                .commitNow()
        }
    }
}