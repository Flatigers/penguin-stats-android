package org.penguin_stats.android.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ResponseItems(
    @PrimaryKey
    val itemId: String,
    val name: String,
    @Embedded @SerializedName("name_i18n")
    val nameI18n: CodeI18N,
    @Embedded
    val existence: Existence,
    val itemType: String,
    val sortId: Int,
    val rarity: Int,
    val groupID: String?,
    val spriteCoord: List<Int>?,
    @Embedded(prefix = "alias")
    val alias: Alias,
    @Embedded(prefix = "pron")
    val pron: Alias,
) {
    data class Alias(
        val zh: List<String>?,
        val en: List<String>?,
        val ja: List<String>?,
        val ko: List<String>?,
    )
}