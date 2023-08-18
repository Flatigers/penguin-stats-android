package org.penguin_stats.android.data


data class TotalStatsUI(
    val sanity: String,
    val reports: String,
    val drops: String,
)

data class ZoneUI(
    val zoneId: String,
    val zoneNameI18n: CodeI18n,
    val existence: Existence,
    val stages: List<String>,
)

data class StageUI(
    val stageId: String,
    val zoneId: String,
    val code_i18n: CodeI18n,
    val existence: Existence,
)