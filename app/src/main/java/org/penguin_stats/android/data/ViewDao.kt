package org.penguin_stats.android.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

object ViewDao {

    fun saveStatsUI(stats: TotalStatsUI) {
        mmkv().encode("stats", Gson().toJson(stats))
    }

    fun saveItemsUI(items: List<ItemUI>) {
        mmkv().encode("items", Gson().toJson(items))
    }

    fun readStatsUI(): TotalStatsUI {
        val data = mmkv().decodeString("stats") ?: ""
        return Gson().fromJson(data, TotalStatsUI::class.java)
    }

    fun readItemsUI(): List<ItemUI> {
        val typeOf = object : TypeToken<List<ItemUI>>() {}.type
        val data = mmkv().decodeString("items") ?: ""
        return Gson().fromJson(data, typeOf)
    }


    fun isStatsUISaved() = mmkv().contains("stats")
    fun isItemsUISaved() = mmkv().contains("items")


    private fun mmkv() = MMKV.mmkvWithID("view_data")
}