package org.penguin_stats.android.data

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.penguin_stats.android.app.BaseApplication

object ViewDao {

    fun saveStatsUI(stats: TotalStatsUI) {
        sharedPreference().edit {
            putString("stats", Gson().toJson(stats))
        }
    }

    fun readStatsUI(): TotalStatsUI {
        val data = sharedPreference().getString("stats", "")
        return Gson().fromJson(data, TotalStatsUI::class.java)
    }

    fun saveZonesUI(zone: List<ZoneUI>) {
        sharedPreference().edit {
            putString("zones", Gson().toJson(zone))
        }
    }

    fun readZonesUI(): List<ZoneUI> {
        val typeOf = object : TypeToken<List<ZoneUI>>() {}.type
        val data = sharedPreference().getString("zones", "")
        return Gson().fromJson(data, typeOf)
    }

    fun saveStagesUI(zone: List<StageUI>) {
        sharedPreference().edit {
            putString("stages", Gson().toJson(zone))
        }
    }

    fun readStagesUI(): List<StageUI> {
        val typeOf = object : TypeToken<List<StageUI>>() {}.type
        val data = sharedPreference().getString("stages", "")
        return Gson().fromJson(data, typeOf)
    }

    fun isStatsUISaved() = sharedPreference().contains("stats")
    fun isZonesUISaved() = sharedPreference().contains("zones")
    fun isStagesUISaved() = sharedPreference().contains("stages")


    private fun sharedPreference() = BaseApplication.context
        .getSharedPreferences("viewData", Context.MODE_PRIVATE)
}