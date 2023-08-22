package org.penguin_stats.android.data

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.penguin_stats.android.app.BaseApplication

object ResponseDao {

    fun saveNotice(notice: List<ResponseNotice>) {
        sharedPreference().edit {
            putString("notice", Gson().toJson(notice))
        }
    }

    fun saveStats(stats: ResponseTotalStats) {
        sharedPreference().edit {
            putString("stats", Gson().toJson(stats))
        }
    }

    fun saveZones(zones: List<ResponseZones>) {
        sharedPreference().edit {
            putString("zones", Gson().toJson(zones))
        }
    }

    fun saveStages(stages: List<ResponseStages>) {
        sharedPreference().edit {
            putString("stages", Gson().toJson(stages))
        }
    }

    fun saveItems(items: List<ResponseItems>) {
        sharedPreference().edit {
            putString("stages", Gson().toJson(items))
        }
    }

    fun readNotice(): List<ResponseNotice> {
        val typeOf = object : TypeToken<List<ResponseNotice>>() {}.type
        val data = sharedPreference().getString("notice", "")
        return Gson().fromJson(data, typeOf)
    }

    fun readStats(): ResponseTotalStats {
        val data = sharedPreference().getString("stats", "")
        return Gson().fromJson(data, ResponseTotalStats::class.java)
    }

    fun readZones(): List<ResponseZones> {
        val typeOf = object : TypeToken<List<ResponseZones>>() {}.type
        val data = sharedPreference().getString("zones", "")
        return Gson().fromJson(data, typeOf)
    }

    fun readStages(): List<ResponseStages> {
        val typeOf = object : TypeToken<List<ResponseStages>>() {}.type
        val data = sharedPreference().getString("stages", "")
        return Gson().fromJson(data, typeOf)
    }

    fun readItems(): List<ResponseItems> {
        val typeOf = object : TypeToken<List<ResponseItems>>() {}.type
        val data = sharedPreference().getString("items", "")
        return Gson().fromJson(data, typeOf)
    }


    fun isNoticeSaved(): Boolean = sharedPreference().contains("notice")
    fun isStatsSaved(): Boolean = sharedPreference().contains("stats")
    fun isZonesSaved(): Boolean = sharedPreference().contains("zones")
    fun isStagesSaved(): Boolean = sharedPreference().contains("stages")
    fun isItemsSaved(): Boolean = sharedPreference().contains("items")


    private fun sharedPreference() = BaseApplication.context
        .getSharedPreferences("response", Context.MODE_PRIVATE)
}