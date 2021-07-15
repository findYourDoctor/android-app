package com.abcd.findyourdoctor.messaging.ui

import android.os.Bundle
import com.abcd.findyourdoctor.BaseActivity
import com.abcd.findyourdoctor.DoctorConstants.Companion.DOCTOR_DATA
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.messaging.entity.SecondUserData

class ChatActivity : BaseActivity() {

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