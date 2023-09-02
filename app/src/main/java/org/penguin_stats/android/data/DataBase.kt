package org.penguin_stats.android.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.penguin_stats.android.app.BaseApplication


@Database(
    version = 1, exportSchema = false,
    entities = [
        ResponseZones::class,
        ResponseStages::class,
        ResponseItems::class
    ]
)
@TypeConverters(
    ListStringConverter::class,
    ListDropInfoConverter::class,
    ListIntConverter::class
)
abstract class PenguinDataBase : RoomDatabase() {

    abstract fun responseZoneDao(): ResponseZonesDao
    abstract fun responseStageDao(): ResponseStagesDao
    abstract fun responseItemsDao(): ResponseItemsDao

    companion object {
        private var instance: PenguinDataBase? = null

        fun getDataBase(): PenguinDataBase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                BaseApplication.context,
                PenguinDataBase::class.java, "penguin_stats_data"
            ).build().apply {
                instance = this
            }
        }

    }
}


/*
 * converters to store
 * List<String> List<DropInfo>
 * in a unit in db
 */
class ListStringConverter {

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

class ListDropInfoConverter {
    @TypeConverter
    fun fromListDropInfo(list: List<ResponseStages.DropInfo>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toListDropInfo(json: String): List<ResponseStages.DropInfo> {
        val type = object : TypeToken<List<ResponseStages.DropInfo>>() {}.type
        return Gson().fromJson(json, type) ?: listOf()
    }
}

class ListIntConverter {

    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        return "${list[0]}:${list[1]}"
    }

    @TypeConverter
    fun toListInt(json: String): List<Int> {
        val a = json.substringBefore(":").toInt()
        val b = json.substringAfter(":").toInt()
        return listOf(a, b)
    }
}