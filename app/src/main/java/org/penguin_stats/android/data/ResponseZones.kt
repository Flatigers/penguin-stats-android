package org.penguin_stats.android.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ResponseZones(
    val zoneId: String, val zoneIndex: Int,
    val type: String, val subType: String,
    val zoneName: String,
    @Embedded @SerializedName("zoneName_i18n") val zoneNameI18n: CodeI18N,
    @Embedded val existence: Existence, val background: String,
    val stages: List<String>,
) {
    @PrimaryKey(autoGenerate = true)
    var responseZonesId: Long = 0
}
