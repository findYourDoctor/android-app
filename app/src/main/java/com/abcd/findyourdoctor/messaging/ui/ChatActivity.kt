package com.abcd.findyourdoctor.messaging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abcd.findyourdoctor.BaseActivity
import com.abcd.findyourdoctor.DoctorConstants.Companion.DOCTOR_DATA
import com.abcd.findyourdoctor.R
import com.abcd.findyourdoctor.doctor.entity.DoctorData

class ChatActivity : BaseActivity() {

    private lateinit var doctorData: DoctorData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        doctorData = intent.getParcelableExtra(DOCTOR_DATA)!!
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance(doctorData))
                .commitNow()
        }
    }
}