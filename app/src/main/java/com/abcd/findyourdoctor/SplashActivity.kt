package com.abcd.findyourdoctor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.abcd.findyourdoctor.dashboard.DashboardActivity
import com.abcd.findyourdoctor.messaging.ChatConstant
import com.abcd.findyourdoctor.util.SharedPreferenceUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

<<<<<<< HEAD
        val isUserIdSaved: Boolean = !TextUtils.isEmpty(SharedPreferenceUtil.getPreferences(this, "userId", ""))
        if (!isUserIdSaved) {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 1000)
=======
        val isUserIdSaved: Boolean = !TextUtils.isEmpty(SharedPreferenceUtil.getPreferences(this, ChatConstant.USER_ID, ""))
        if (!isUserIdSaved) {
            SharedPreferenceUtil.setPreferences(this, ChatConstant.USER_ID, System.currentTimeMillis().toString())
            startHandler()
>>>>>>> commit for chat adapter
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