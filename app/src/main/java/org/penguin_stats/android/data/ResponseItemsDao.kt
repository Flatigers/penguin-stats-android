package org.penguin_stats.android.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ResponseItemsDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ResponseItems): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems(items: List<ResponseItems>)

    @Transaction
    @Update
    fun updateItem(newItem: ResponseItems)

    @Transaction
    @Query("select * from ResponseItems")
    fun loadAllItems(): List<ResponseItems>

    @Transaction
    @Query("select * from ResponseItems where itemId=:id")
    fun loadItemById(id: String): ResponseItems
}