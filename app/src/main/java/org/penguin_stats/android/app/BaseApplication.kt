package org.penguin_stats.android.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}