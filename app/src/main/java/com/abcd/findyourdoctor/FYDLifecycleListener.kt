package com.abcd.findyourdoctor

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class FYDLifecycleListener : LifecycleObserver {
	
	    @OnLifecycleEvent(Lifecycle.Event.ON_START)
	    fun onMoveToForeground() {
	        Log.d("SampleLifecycle", "Returning to foreground…")
	    }
	
	    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
	    fun onMoveToBackground() {
	        Log.d("SampleLifecycle", "Moving to background…")
	    }
	}