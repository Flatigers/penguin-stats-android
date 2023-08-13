package org.penguin_stats.android.util

import android.content.res.ColorStateList

fun buildColorList(pressedC: Int, defaultC: Int): ColorStateList {
    return ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()),
        intArrayOf(
            defaultC,
            pressedC,
        )
    )
}