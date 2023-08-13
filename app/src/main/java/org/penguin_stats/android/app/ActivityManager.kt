package org.penguin_stats.android.app

import android.app.Activity
import java.util.ArrayList

object ActivityManager {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAllActivity() {
        for (x in activities) {
            if (!x.isFinishing) {
                x.finish()
            }
        }
        activities.clear()
        android.os.Process.killProcess(android.os.Process.myPid())
    }

}