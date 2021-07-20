package com.app.chatmodule.messaging.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.app.chatmodule.DoctorConstants.Companion.DOCTOR_DATA
import com.app.chatmodule.R
import com.app.chatmodule.messaging.entity.SecondUserData

class ChatActivity : AppCompatActivity() {

    private lateinit var userData: SecondUserData
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        userData = intent.getParcelableExtra(DOCTOR_DATA)!!
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance(userData))
                .commitNow()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        val txtUserName = toolbar.findViewById<TextView>(R.id.txtUserName)
        txtUserName.text = userData.name
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}