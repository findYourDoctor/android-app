package com.abcd.findyourdoctor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.abcd.findyourdoctor.dashboard.DashboardActivity
import com.app.chatmodule.messaging.ChatConstant
import com.app.chatmodule.util.SharedPreferenceUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val isUserIdSaved: Boolean =
            !TextUtils.isEmpty(SharedPreferenceUtil.getPreferences(this, ChatConstant.USER_ID, ""))
        if (!isUserIdSaved) {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
//                startActivity(Intent(this, LoginActivity::class.java))
                SharedPreferenceUtil.setPreferences(
                    this,
                    ChatConstant.USER_ID,
                    /*"1625952788444"*/
                    System.currentTimeMillis().toString()
                )
                startActivity(
                    Intent(
                        this@SplashActivity,
                        DashboardActivity
                        ::class.java
                    )
                )
                finish()
            }, 1000)
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