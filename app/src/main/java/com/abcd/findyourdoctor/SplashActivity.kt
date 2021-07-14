package com.abcd.findyourdoctor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.abcd.findyourdoctor.dashboard.DashboardActivity
import com.abcd.findyourdoctor.doctor.DoctorListActivity
import com.abcd.findyourdoctor.messaging.ui.ChatActivity
import com.abcd.findyourdoctor.util.SharedPreferenceUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isUserIdSaved: Boolean = SharedPreferenceUtil.getLongPreferences(this, "userId", 0) > 0
        if (!isUserIdSaved) {
            SharedPreferenceUtil.setLongPreferences(this, "userId", 1625952788444)
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
                    DashboardActivity
                    ::class.java
                )
            )
            finish()
        }, 1000)
    }
}