package org.penguin_stats.android.data

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/*
 * Class Below is of public Entity
 */
@Entity
data class CodeI18N(
    val en: String, val ja: String, val ko: String, val zh: String,
)

@Entity
data class Existence(
    @Embedded(prefix = "cn_") @SerializedName("CN")
    val cn: ExistenceValue,
    @Embedded(prefix = "jp_") @SerializedName("JP")
    val jp: ExistenceValue,
    @Embedded(prefix = "kr_") @SerializedName("KR")
    val kr: ExistenceValue,
    @Embedded(prefix = "us_") @SerializedName("US")
    val us: ExistenceValue,
) {
    data class ExistenceValue(val exist: Boolean, val openTime: Long, val closeTime: Long)
}
