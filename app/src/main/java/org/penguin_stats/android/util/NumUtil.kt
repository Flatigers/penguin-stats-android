package org.penguin_stats.android.util

fun genSplitNum(num: Number): String {
    return num.toString().reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}