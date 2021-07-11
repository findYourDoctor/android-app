package com.abcd.findyourdoctor

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.R
import android.util.Log

import androidx.annotation.NonNull

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
        val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val userId = sharedPref.getLong("userId", 0)
        return if (userId > receiverId.toLong()) {
            (userId.toString() + receiverId)
        } else {
            (receiverId + userId.toString())
        }
    }

    fun getUserId(): String {
        val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
        return sharedPref.getLong("userId", 0).toString()
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