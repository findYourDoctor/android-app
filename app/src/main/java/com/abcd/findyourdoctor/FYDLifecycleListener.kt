package com.abcd.findyourdoctor

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.app.chatmodule.messaging.ChatConstant
import com.app.chatmodule.util.SharedPreferenceUtil
import com.google.firebase.database.FirebaseDatabase

class FYDLifecycleListener(private val fydApplication: FYDApplication) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        Log.d("Lifecycle", "Returning to foreground…")

//        if (!TextUtils.isEmpty(
//                SharedPreferenceUtil.getPreferences(
//                    fydApplication,
//                    ChatConstant.USER_ID,
//                    ""
//                )
//            )
//        ) {
//            FirebaseDatabase.getInstance().reference.child("doctor").child(
//                SharedPreferenceUtil.getPreferences(
//                    fydApplication,
//                    ChatConstant.USER_ID,
//                    ""
//                )
//            ).child("online").setValue(true)
//        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onMoveToBackground() {
//        if (!TextUtils.isEmpty(
//                SharedPreferenceUtil.getPreferences(
//                    fydApplication,
//                    ChatConstant.USER_ID,
//                    ""
//                )
//            )
//        ) {
//            FirebaseDatabase.getInstance().reference.child("doctor").child(
//                SharedPreferenceUtil.getPreferences(
//                    fydApplication,
//                    ChatConstant.USER_ID,
//                    ""
//                )
//            ).child("online").setValue(false)
//        }
    }
}