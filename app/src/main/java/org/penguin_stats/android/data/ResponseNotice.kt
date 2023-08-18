package org.penguin_stats.android.data

data class ResponseNotice(
    val id: Int, val existence: Existence,
    val severity: Int, val content_i18n: CodeI18n,
)
