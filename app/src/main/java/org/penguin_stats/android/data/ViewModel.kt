package org.penguin_stats.android.data


data class TotalStatsUI(
    val sanity: String,
    val reports: String,
    val drops: String,
)

data class ZoneUI(
    val zoneId: String,
    val type: String,
    val zoneNameI18n: CodeI18N,
    val existence: Existence,
    val background: String?,
    val stages: List<String>,
)

data class StageUI(
    val stageId: String,
    val zoneId: String,
    val code_i18n: CodeI18N,
    val existence: Existence,
)

data class ItemUI(
    val itemId: String,
    val name_i18n: CodeI18N,
    val existence: Existence,
    val itemType: String,
    val spriteCoord: List<Int>?,
)