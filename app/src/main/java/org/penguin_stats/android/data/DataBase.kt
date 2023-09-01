package org.penguin_stats.android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Database(version = 1, entities = [ResponseZones::class])
@TypeConverters(ListConverter::class)
abstract class PenguinDataBase : RoomDatabase() {

    abstract fun responseZoneDao(): ResponseZoneDao

    companion object {
        private var instance: PenguinDataBase? = null

        fun getDataBase(context: Context): PenguinDataBase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                PenguinDataBase::class.java, "penguin_data_base"
            )
                .build().apply {
                    instance = this
                }
        }

    }
}


/*
 * Converter for ROOM to store List<String>
 */
class ListConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type) ?: listOf("")
    }
}
