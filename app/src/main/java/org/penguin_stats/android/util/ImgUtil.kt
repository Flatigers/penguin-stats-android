package org.penguin_stats.android.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import org.penguin_stats.android.R

fun itemScaledImg(context: Context, coordinate: List<Int>?, bitmap: Bitmap): Drawable {
    val resource = context.resources
    if (coordinate == null) return ResourcesCompat.getDrawable(
        resource,
        R.drawable.default_item,
        context.theme
    )!!
    val length = bitmap.width / 6
    val x = coordinate[0] * length
    val y = coordinate[1] * length
    val sub = Bitmap.createBitmap(bitmap, x, y, length, length)
    return sub.toDrawable(resource)
}