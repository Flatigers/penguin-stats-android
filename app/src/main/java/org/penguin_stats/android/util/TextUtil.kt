package org.penguin_stats.android.util

import androidx.annotation.RawRes

fun genSplitNum(num: Number): String {
    return num.toString().reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}

fun rawUriStr(@RawRes id: Int): String =
    "file:///android_res/raw/$id"
