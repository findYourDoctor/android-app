package com.abcd.findyourdoctor

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class FYDApplication : Application() {
    private val lifecycleListener: FYDLifecycleListener by lazy {
        FYDLifecycleListener()
    }

    override fun onCreate() {
        super.onCreate()
        setupLifecycleListener()
    }

    private fun setupLifecycleListener() {
        ProcessLifecycleOwner.get().lifecycle
            .addObserver(lifecycleListener)
    }
}