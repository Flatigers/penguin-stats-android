package org.penguin_stats.android.data

data class ResponseItems(
    val itemId: String, val name: String,
    val name_i18n: CodeI18N, val existence: Existence,
    val itemType: String, val sortId: Int,
    val rarity: Int, val groupID: String,
    val spriteCoord: List<Int>?, val alias: Alias,
    val pron: Alias
) {
    data class Alias(
        val zh: List<String>,
        val en: List<String>,
        val ja: List<String>,
        val ko: List<String>,
    )
}