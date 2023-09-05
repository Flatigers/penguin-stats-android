package org.penguin_stats.android.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ResultMatrixDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatrix(matrix: ResultMatrix): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMatrix(matrix: List<ResultMatrix>)

    @Transaction
    @Update
    fun updateMatrix(newMatrix: ResultMatrix)

    @Transaction
    @Delete
    fun deleteMatrix(matrix: ResultMatrix)

    @Transaction
    @Query("select * from ResultMatrix")
    fun loadAllMatrix(): List<ResultMatrix>

    @Transaction
    @Query("select * from ResultMatrix where stageId=:stageId")
    fun loadAllMatrixByStageId(stageId: String): List<ResultMatrix>

    @Transaction
    @Query("select * from ResultMatrix where itemId=:itemId")
    fun loadAllMatrixByItemId(itemId: String): List<ResultMatrix>
}