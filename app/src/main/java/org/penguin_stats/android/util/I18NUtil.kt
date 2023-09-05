package org.penguin_stats.android.util

import android.util.Log
import org.penguin_stats.android.app.AppConfig
import org.penguin_stats.android.data.CodeI18N
import org.penguin_stats.android.data.Existence
import java.util.Locale

fun getLocale(): Locale {
    return when (AppConfig.getServer()) {
        "CN" -> Locale.CHINA
        "US" -> Locale.US
        "JP" -> Locale.JAPAN
        "KR" -> Locale.KOREA
        else -> Locale.CHINA
    }
}

fun codeFromI18N(codeI18n: CodeI18N, existence: Existence) =
    when (AppConfig.getServer()) {
        "CN" -> if (existence.cn.exist) codeI18n.zh else ""
        "US" -> if (existence.us.exist) codeI18n.en else ""
        "JP" -> if (existence.jp.exist) codeI18n.ja else ""
        "KR" -> if (existence.kr.exist) codeI18n.ko else ""
        else -> "null"
    }

fun filterI18NExistence(existence: Existence): Boolean {
    return when (AppConfig.getServer()) {
        "CN" -> existence.cn.exist
        "US" -> existence.us.exist
        "JP" -> existence.jp.exist
        "KR" -> existence.kr.exist
        else -> false
    }
}

fun filterI18NTime(existence: Existence): Boolean {
    val time = System.currentTimeMillis()
    Log.e("time", time.toString())
    Log.e("time", existence.cn.closeTime.toString())
    return when (AppConfig.getServer()) {
        "CN" -> existence.cn.closeTime > time && existence.cn.openTime < time
        "US" -> existence.us.closeTime > time && existence.us.openTime < time
        "JP" -> existence.jp.closeTime > time && existence.jp.openTime < time
        "KR" -> existence.kr.closeTime > time && existence.kr.openTime < time
        else -> false
    }
}