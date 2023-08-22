package org.penguin_stats.android.util

import android.util.Log
import org.penguin_stats.android.app.AppConfig
import org.penguin_stats.android.data.CodeI18N
import org.penguin_stats.android.data.Existence

fun codeFromI18N(codeI18n: CodeI18N, existence: Existence) =
    when (AppConfig.getServer()) {
        "CN" -> if (existence.CN.exist) codeI18n.zh else ""
        "US" -> if (existence.US.exist) codeI18n.en else ""
        "JP" -> if (existence.JP.exist) codeI18n.ja else ""
        "KR" -> if (existence.KR.exist) codeI18n.ko else ""
        else -> "null"
    }

fun filterI18NExistence(existence: Existence): Boolean {
    return when (AppConfig.getServer()) {
        "CN" -> existence.CN.exist
        "US" -> existence.US.exist
        "JP" -> existence.JP.exist
        "KR" -> existence.KR.exist
        else -> false
    }
}

fun filterI18NTime(existence: Existence): Boolean {
    val time = System.currentTimeMillis()
    Log.e("time", time.toString())
    Log.e("time", existence.CN.closeTime.toString())
    return when (AppConfig.getServer()) {
        "CN" -> existence.CN.closeTime > time && existence.CN.openTime < time
        "US" -> existence.US.closeTime > time && existence.US.openTime < time
        "JP" -> existence.JP.closeTime > time && existence.JP.openTime < time
        "KR" -> existence.KR.closeTime > time && existence.KR.openTime < time
        else -> false
    }
}