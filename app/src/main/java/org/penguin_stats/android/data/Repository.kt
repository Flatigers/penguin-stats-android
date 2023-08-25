package org.penguin_stats.android.data

import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.penguin_stats.android.network.Network
import org.penguin_stats.android.util.genSplitNum

object Repository {

    fun isNoticeSaved() = ResponseDao.isNoticeSaved()
    fun isStatsSaved() = ResponseDao.isStatsSaved()
    fun isZonesSaved() = ResponseDao.isZonesSaved()
    fun isStagesSaved() = ResponseDao.isStagesSaved()
    fun isItemsSaved() = ResponseDao.isItemsSaved()
    fun isStatsUISaved() = ViewDao.isStatsUISaved()
    fun isZonesUISaved() = ViewDao.isZonesUISaved()
    fun isStagesUISaved() = ViewDao.isStagesUISaved()
    fun isItemsUISaved() = ViewDao.isItemsUISaved()

    fun readStatsUI() = ViewDao.readStatsUI()
    fun readZonesUI() = ViewDao.readZonesUI()
    fun readStagesUI() = ViewDao.readStagesUI()
    fun readItemsUI() = ViewDao.readItemsUI()

    fun readNotice() = ResponseDao.readNotice()
    fun readStats() = ResponseDao.readStats()
    fun readZones() = ResponseDao.readZones()
    fun readStages() = ResponseDao.readStages()
    fun readItems() = ResponseDao.readItems()

    suspend fun saveNotice() = coroutineScope {
        runBlocking {
            try {
                val response = Network.getNotice()
                ResponseDao.saveNotice(response)
            } catch (e: Exception) {
                Log.e("E-repo: Notice", e.toString())
            }
        }
    }

    suspend fun saveStats() = coroutineScope {
        runBlocking {
            try {
                val response = Network.getTotalStats()
                ResponseDao.saveStats(response)
                val statsUI = TotalStatsUI(
                    genSplitNum(response.totalApCost),
                    genSplitNum(response.totalReports.sumOf { it.times }),
                    genSplitNum(response.totalItemQuantities.sumOf { it.quantity })
                )
                ViewDao.saveStatsUI(statsUI)
            } catch (e: Exception) {
                Log.e("E-repo: Stats", e.toString())
            }
        }
    }

    suspend fun saveZones() = coroutineScope {
        runBlocking {
            try {
                val response = Network.getZones()
                ResponseDao.saveZones(response)
                val zoneUI = mutableListOf<ZoneUI>()
                response.forEach {
                    zoneUI.add(
                        ZoneUI(
                            it.zoneId,
                            it.type,
                            it.zoneName_i18n,
                            it.existence,
                            it.background,
                            it.stages
                        )
                    )
                }
                ViewDao.saveZonesUI(zoneUI)
            } catch (e: Exception) {
                Log.e("E-repo: Zones", e.toString())
            }
        }
    }

    suspend fun saveStages() = coroutineScope {
        runBlocking {
            try {
                val response = Network.getStages()
                ResponseDao.saveStages(response)
                val stageUI = mutableListOf<StageUI>()
                response.forEach {
                    stageUI.add(
                        StageUI(
                            it.stageId,
                            it.zoneId,
                            it.code_i18n,
                            it.existence
                        )
                    )
                }
                ViewDao.saveStagesUI(stageUI)
            } catch (e: Exception) {
                Log.e("E-repo: Stages", e.toString())
            }
        }
    }

    suspend fun saveItems() = coroutineScope {
        runBlocking {
            try {
                val response = Network.getItems()
                ResponseDao.saveItems(response)
                val itemsUI = mutableListOf<ItemUI>()
                response.forEach {
                    itemsUI.add(
                        ItemUI(
                            it.itemId,
                            it.name_i18n,
                            it.existence,
                            it.itemType,
                            it.spriteCoord
                        )
                    )
                }
                ViewDao.saveItemsUI(itemsUI)
            } catch (e: Exception) {
                Log.e("E-repo: Items", e.toString())
            }
        }
    }

}