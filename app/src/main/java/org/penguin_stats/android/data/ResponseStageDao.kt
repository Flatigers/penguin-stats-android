package org.penguin_stats.android.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ResponseStageDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStage(stage: ResponseStages): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllStages(stages: List<ResponseStages>)

    @Transaction
    @Update
    fun updateStage(newStage: ResponseStages)

    @Transaction
    @Query("select * from ResponseStages")
    fun loadAllStages(): List<ResponseStages>

    @Transaction
    @Query("select * from ResponseStages where stageId=:id")
    fun loadStageByStageId(id: String): ResponseStages
}