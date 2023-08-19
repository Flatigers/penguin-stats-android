package org.penguin_stats.android.data

data class CodeI18N(
    val en: String, val ja: String, val ko: String, val zh: String
)

data class Existence(
    val CN: ExistenceValue, val JP: ExistenceValue, val KR: ExistenceValue, val US: ExistenceValue
) {
    data class ExistenceValue(val exist: Boolean, val openTime: Long, val closeTime: Long)
}

object DefaultI18N {
    @JvmStatic
    fun defaultCodeI18N() =
        CodeI18N("default", "default", "default", "default")


    fun defaultExistence(): Existence {
        val exv = Existence.ExistenceValue(false, 0, 0)
        return Existence(exv, exv, exv, exv)
    }
}