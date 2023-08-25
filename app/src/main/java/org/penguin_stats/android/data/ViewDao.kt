package org.penguin_stats.android.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

object ViewDao {

    fun saveStatsUI(stats: TotalStatsUI) {
        mmkv().encode("stats", Gson().toJson(stats))
    }

    fun saveZonesUI(zones: List<ZoneUI>) {
        mmkv().encode("zones", Gson().toJson(zones))
    }

    fun saveStagesUI(stages: List<StageUI>) {
        mmkv().encode("stages", Gson().toJson(stages))
    }

    fun saveItemsUI(items: List<ItemUI>) {
        mmkv().encode("items", Gson().toJson(items))
    }

    fun readStatsUI(): TotalStatsUI {
        val data = mmkv().decodeString("stats")
        return Gson().fromJson(data, TotalStatsUI::class.java)
    }

    fun readZonesUI(): List<ZoneUI> {
        val typeOf = object : TypeToken<List<ZoneUI>>() {}.type
        val data = mmkv().decodeString("zones")
        return Gson().fromJson(data, typeOf)
    }

    fun readStagesUI(): List<StageUI> {
        val typeOf = object : TypeToken<List<StageUI>>() {}.type
        val data = mmkv().decodeString("stages")
        return Gson().fromJson(data, typeOf)
    }

    fun readItemsUI(): List<ItemUI> {
        val typeOf = object : TypeToken<List<ItemUI>>() {}.type
        val data = mmkv().decodeString("items")
        return Gson().fromJson(data, typeOf)
    }


    fun isStatsUISaved() = mmkv().contains("stats")
    fun isZonesUISaved() = mmkv().contains("zones")
    fun isStagesUISaved() = mmkv().contains("stages")
    fun isItemsUISaved() = mmkv().contains("items")


    private fun mmkv() = MMKV.mmkvWithID("view_data")
}