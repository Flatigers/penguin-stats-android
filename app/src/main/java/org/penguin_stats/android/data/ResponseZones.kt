package org.penguin_stats.android.data

data class ResponseZones(
    val zoneId: String, val zoneIndex: Int,
    val type: String, val subType: String,
    val zoneName: String, val zoneName_i18n: CodeI18n,
    val existence: Existence, val background: String,
    val stages: List<String>,
)
