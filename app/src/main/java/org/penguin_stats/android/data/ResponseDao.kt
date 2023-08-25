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

    fun saveZones(zones: List<ResponseZones>) {
        mmkv().encode("zones", Gson().toJson(zones))
    }

    fun saveStages(stages: List<ResponseStages>) {
        mmkv().encode("stages", Gson().toJson(stages))
    }

    fun saveItems(items: List<ResponseItems>) {
        mmkv().encode("items", Gson().toJson(items))
    }

    fun readNotice(): List<ResponseNotice> {
        val typeOf = object : TypeToken<List<ResponseNotice>>() {}.type
        val data = mmkv().decodeString("notice")
        return Gson().fromJson(data, typeOf)
    }

    fun readStats(): ResponseTotalStats {
        val data = mmkv().decodeString("stats")
        return Gson().fromJson(data, ResponseTotalStats::class.java)
    }

    fun readZones(): List<ResponseZones> {
        val typeOf = object : TypeToken<List<ResponseZones>>() {}.type
        val data = mmkv().decodeString("zones")
        return Gson().fromJson(data, typeOf)
    }

    fun readStages(): List<ResponseStages> {
        val typeOf = object : TypeToken<List<ResponseStages>>() {}.type
        val data = mmkv().decodeString("stages")
        return Gson().fromJson(data, typeOf)
    }

    fun readItems(): List<ResponseItems> {
        val typeOf = object : TypeToken<List<ResponseItems>>() {}.type
        val data = mmkv().decodeString("items")
        return Gson().fromJson(data, typeOf)
    }


    fun isNoticeSaved(): Boolean = mmkv().contains("notice")
    fun isStatsSaved(): Boolean = mmkv().contains("stats")
    fun isZonesSaved(): Boolean = mmkv().contains("zones")
    fun isStagesSaved(): Boolean = mmkv().contains("stages")
    fun isItemsSaved(): Boolean = mmkv().contains("items")


    private fun mmkv() = MMKV.mmkvWithID("response_data")
}