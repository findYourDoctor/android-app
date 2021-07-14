package com.abcd.findyourdoctor.messaging.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abcd.findyourdoctor.DoctorConstants
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.doctor.entity.DoctorData
import com.abcd.findyourdoctor.messaging.entity.SecondUserData
import com.abcd.findyourdoctor.messaging.ui.ChatFragment

class DoctorChatActivity : AppCompatActivity() {

    private lateinit var doctorData: SecondUserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        doctorData = intent.getParcelableExtra("UserData")!!
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance(doctorData))
                .commitNow()
        }
    }
}