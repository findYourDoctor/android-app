package com.app.chatmodule.messaging.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.app.chatmodule.DoctorConstants.Companion.DOCTOR_DATA
import com.app.chatmodule.R
import com.app.chatmodule.messaging.entity.SecondUserData

class ChatActivity : FragmentActivity() {

    private lateinit var userData: SecondUserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        userData = intent.getParcelableExtra(DOCTOR_DATA)!!
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance(userData))
                .commitNow()
        }
    }
}