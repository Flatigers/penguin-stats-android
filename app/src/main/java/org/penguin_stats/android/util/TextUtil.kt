package org.penguin_stats.android.util

import androidx.annotation.RawRes
import org.penguin_stats.android.R
import org.penguin_stats.android.app.BaseApplication
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat

/*
 * Split a number with ','
 * eg:
 * Input:  1200000
 * Output: 1,200,000
 */
fun Number.genSplitNum(): String {
    return this.toString().reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}

/*
 * Get uri of /res/raw/ file
 */
fun rawUriStr(@RawRes id: Int): String =
    "file:///android_res/raw/$id"


fun InputStream.inputStreamToStr(): String {
    val stringBuilder = StringBuilder()
    val bufferedReader = BufferedReader(InputStreamReader(this))

    var line: String? = bufferedReader.readLine()
    while (line != null) {
        stringBuilder.append(line).append("\n")
        line = bufferedReader.readLine()
    }

    bufferedReader.close()
    return stringBuilder.toString()
}

fun Long.millsToMinSec(): String {
    val res = BaseApplication.context.resources
    val min = this / 1000 / 60
    val sec = "%.1f".format(this.toDouble() / 1000 % 60)
    val minS = res.getString(R.string.minutes)
    val secS = res.getString(R.string.seconds)
    return "$min $minS $sec $secS"
}

fun periodTimeString(start: Long, end: Long?): String {
    val res = BaseApplication.context.resources
    val dFormat = SimpleDateFormat("yy.MM.dd", getLocale())
    val sTime = dFormat.format(start)
    val eTime = if (end == null) {
        res.getString(R.string.till_now)
    } else {
        "~ ${dFormat.format(end)}"
    }

    return "$sTime $eTime"
}