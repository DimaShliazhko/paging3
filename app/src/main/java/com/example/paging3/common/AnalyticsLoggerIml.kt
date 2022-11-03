package com.example.paging3.common

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class AnalyticsLoggerIml : AnalyticsLogger, LifecycleEventObserver {

    override fun registrationLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("ActivityLifecycle", "ON_CREATE")
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.d("ActivityLifecycle", "ON_PAUSE")
            }
            Lifecycle.Event.ON_STOP -> {
                Log.d("ActivityLifecycle", "ON_STOP")
            }
            Lifecycle.Event.ON_RESUME -> {
                Log.d("ActivityLifecycle", "ON_RESUME")
            }
            else -> Unit
        }
    }
}