package org.penguin_stats.android.util

import androidx.annotation.RawRes
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun genSplitNum(num: Number): String {
    return num.toString().reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}

fun rawUriStr(@RawRes id: Int): String =
    "file:///android_res/raw/$id"

fun inputStreamToStr(input: InputStream): String {
    val stringBuilder = StringBuilder()
    val bufferedReader = BufferedReader(InputStreamReader(input))

    var line: String? = bufferedReader.readLine()
    while (line != null) {
        stringBuilder.append(line)
        line = bufferedReader.readLine()
    }

    bufferedReader.close()
    return stringBuilder.toString()
}