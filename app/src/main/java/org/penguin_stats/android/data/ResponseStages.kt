package org.penguin_stats.android.data

data class ResponseStages(
    val stageId: String, val zoneId: String,
    val stageType: String, val code: String,
    val code_i18n: CodeI18n, val apCost: Int,
    val existence: Existence, val minClearTime: Long,
    val dropInfos: List<DropInfo>
) {

    data class DropInfo(
        val itemId: String, val dropType: String, val bounds: Bounds
    ) {
        data class Bounds(val lower: Int, val upper: Int)
    }
}
