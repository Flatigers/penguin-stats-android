package org.penguin_stats.android.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tencent.mmkv.MMKV

object ResponseDao {

    fun saveNotice(notice: List<ResponseNotice>) {
        mmkv().encode("notice", Gson().toJson(notice))
    }

    fun saveStats(stats: ResponseTotalStats) {
        mmkv().encode("stats", Gson().toJson(stats))
    }

    fun readNotice(): List<ResponseNotice> {
        val typeOf = object : TypeToken<List<ResponseNotice>>() {}.type
        val data = mmkv().decodeString("notice")
        return Gson().fromJson(data, typeOf)
    }

    fun isNoticeSaved(): Boolean = mmkv().contains("notice")


    private fun mmkv() = MMKV.mmkvWithID("response_data")
}