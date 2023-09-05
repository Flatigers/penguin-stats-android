package org.penguin_stats.android.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ResultPattern(
    val stageId: String,
    @Embedded
    val pattern: Pattern,
    val times: Long,
    val quantity: Long,
    val start: Long,
    val end: Long?,
) {

    @PrimaryKey(autoGenerate = true)
    var key: Long = 0

    data class Pattern(val drops: List<Drops>) {
        data class Drops(val itemId: String, val quantity: Long)
    }
}

data class ResultPatternNetwork(
    @SerializedName("pattern_matrix")
    val patternMatrix: List<ResultPattern>,
)