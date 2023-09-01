package org.penguin_stats.android.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface ResponseZoneDao {

    @Transaction
    @Insert
    fun insertZone(zone: ResponseZones): Long

    @Transaction
    @Update
    fun updateZone(newZone: ResponseZones)

    @Transaction
    @Query("select * from ResponseZones")
    fun loadAllZones(): List<ResponseZones>

    @Transaction
    @Query("select * from ResponseZones where type=:type")
    fun loadZonesByType(type: String): List<ResponseZones>

}