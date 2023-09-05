package org.penguin_stats.android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ResultPatternDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPattern(pattern: ResultPattern): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPatterns(pattern: List<ResultPattern>)

    @Transaction
    @Update
    fun updatePattern(newPattern: ResultPattern)

    @Transaction
    @Delete
    fun deletePattern(pattern: ResultPattern)

    @Transaction
    @Query("select * from ResultPattern")
    fun loadAllPatterns(): List<ResultPattern>

    @Transaction
    @Query("select * from ResultPattern where stageId=:stageId")
    fun loadPatternsByStageId(stageId: String): List<ResultPattern>
}