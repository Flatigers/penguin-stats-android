package org.penguin_stats.android.data

data class CodeI18n(
    val en: String, val ja: String, val ko: String, val zh: String
)

data class Existence(
    val CN: ExistenceValue, val JP: ExistenceValue, val KR: ExistenceValue, val US: ExistenceValue
) {
    data class ExistenceValue(val exist: Boolean, val openTime: Long, val closeTime: Long)
}