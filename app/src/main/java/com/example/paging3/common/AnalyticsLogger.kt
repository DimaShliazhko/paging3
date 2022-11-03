package com.example.paging3.common

import androidx.lifecycle.LifecycleOwner

interface AnalyticsLogger {
    fun registrationLifecycleOwner(lifecycleOwner: LifecycleOwner)
}