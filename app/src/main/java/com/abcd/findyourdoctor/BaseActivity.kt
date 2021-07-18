package com.abcd.findyourdoctor

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.R
import android.util.Log

import androidx.annotation.NonNull
import com.abcd.findyourdoctor.messaging.ChatConstant
import com.abcd.findyourdoctor.util.SharedPreferenceUtil

import com.google.android.gms.tasks.OnCompleteListener

import com.google.firebase.messaging.FirebaseMessaging




abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgressDialog() {
        progressDialog = ProgressDialog(this@BaseActivity)
        progressDialog.setTitle("Please Wait")
        progressDialog.show()
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    fun getChatId(receiverId : String) : String {
        val userId = SharedPreferenceUtil.getPreferences(this, ChatConstant.USER_ID, "")
        return if (userId > receiverId) {
            (userId + receiverId)
        } else {
            (receiverId + userId)
        }
    }

    fun getUserId(): String {
        return SharedPreferenceUtil.getPreferences(this, ChatConstant.USER_ID, 0).toString()
    }

    fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String? = task.result

//                // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
//                Log.d("TAG", msg)
//                Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
            })
    }
}