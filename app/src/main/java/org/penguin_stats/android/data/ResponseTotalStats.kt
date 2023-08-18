package org.penguin_stats.android.data

import com.google.gson.annotations.SerializedName

data class ResponseTotalStats(
    val totalApCost: Long,
    @SerializedName("totalStageTimes") val totalReports: List<StageTimes>,
    @SerializedName("totalItemQuantities") val totalItemQuantities: List<ItemQuantities>,
) {
    data class StageTimes(
        val stageId: String,
        val times: Long
    )

    data class ItemQuantities(
        val itemId: String,
        val quantity: Long,
    )
}
