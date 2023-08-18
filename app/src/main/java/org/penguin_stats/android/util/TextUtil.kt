package org.penguin_stats.android.util

import androidx.annotation.RawRes
import org.penguin_stats.android.app.AppConfig
import org.penguin_stats.android.data.CodeI18n
import org.penguin_stats.android.data.Existence

fun rawUriStr(@RawRes id: Int): String =
    "file:///android_res/raw/$id"

fun codeFromI18N(codeI18n: CodeI18n, existence: Existence) =
    when (AppConfig.getServer()) {
        "CN" -> if (existence.CN.exist) codeI18n.zh else "null"
        "US" -> if (existence.US.exist) codeI18n.en else "null"
        "JP" -> if (existence.JP.exist) codeI18n.ja else "null"
        "KR" -> if (existence.KR.exist) codeI18n.ko else "null"
        else -> {
            "null"
        }
    }
