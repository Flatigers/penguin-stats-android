package org.penguin_stats.android.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ResponseStages(
    @PrimaryKey val stageId: String,
    val zoneId: String,
    val stageType: String?,
    val code: String?,
    @Embedded @SerializedName("code_i18n")
    val codeI18n: CodeI18N,
    val apCost: Int,
    @Embedded
    val existence: Existence,
    val minClearTime: Long?,
    val dropInfos: List<DropInfo>?,
) {

    data class DropInfo(
        val itemId: String,
        val dropType: String,
        val bounds: Bounds,
    ) {
        data class Bounds(val lower: Int, val upper: Int)
    }


}
