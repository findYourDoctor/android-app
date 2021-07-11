package com.abcd.findyourdoctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.abcd.findyourdoctor.doctor.DoctorListActivity
import com.abcd.findyourdoctor.messaging.ui.ChatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val isUserIdSaved: Boolean = sharedPref.getLong("userId", 0) > 0
        if (!isUserIdSaved) {
            with(sharedPref.edit()) {
                putLong("userId", System.currentTimeMillis())
                apply()
            }
            startHandler()
        } else {
            startHandler()
        }
    }

    private fun startHandler() {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(
                Intent(
                    this@SplashActivity,
                    DoctorListActivity
                    ::class.java
                )
            )
            finish()
        }, 1000)
    }
}