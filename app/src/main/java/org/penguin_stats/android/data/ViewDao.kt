package org.penguin_stats.android.data

import com.google.gson.Gson
import com.tencent.mmkv.MMKV

object ViewDao {

    fun saveStatsUI(stats: TotalStatsUI) {
        mmkv().encode("stats", Gson().toJson(stats))
    }

    fun readStatsUI(): TotalStatsUI {
        val data = mmkv().decodeString("stats") ?: ""
        return Gson().fromJson(data, TotalStatsUI::class.java)
    }


    fun isStatsUISaved() = mmkv().contains("stats")

    private fun mmkv() = MMKV.mmkvWithID("view_data")
}