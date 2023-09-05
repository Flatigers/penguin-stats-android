package org.penguin_stats.android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultMatrix(
    val stageId: String,
    val itemId: String,
    val times: Long,
    val quantity: Long,
    val stdDev: Float,
    val start: Long,
    val end: Long?,
) {
    @PrimaryKey(autoGenerate = true)
    var key: Long = 0
}

data class ResultMatrixNetwork(
    val matrix: List<ResultMatrix>,
)