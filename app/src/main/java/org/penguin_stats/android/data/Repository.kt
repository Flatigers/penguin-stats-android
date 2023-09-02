package org.penguin_stats.android.data

import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.penguin_stats.android.network.Network
import org.penguin_stats.android.util.genSplitNum

object Repository {

    fun isNoticeSaved() = ResponseDao.isNoticeSaved()
    fun isStatsUISaved() = ViewDao.isStatsUISaved()
    fun isItemsUISaved() = ViewDao.isItemsUISaved()

    fun readNotice() = ResponseDao.readNotice()
    fun readStatsUI() = ViewDao.readStatsUI()
    fun readAllZones() = responseZoneDao().loadAllZones()
    fun readZonesByType(type: String) = responseZoneDao().loadZonesByType(type)
    fun readAllStages() = responseStageDao().loadAllStages()
    fun readStagesById(id: String) = responseStageDao().loadStageByStageId(id)
    fun readItemsUI() = ViewDao.readItemsUI()


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
                responseZoneDao().insertAllZones(response)
            } catch (e: Exception) {
                Log.e("E-repo: Zones", e.toString())
            }
        }
    }

    suspend fun saveStages() = coroutineScope {
        runBlocking {
            try {
                val response = Network.getStages()
                responseStageDao().insertAllStages(response)
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
                            it.nameI18n,
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

    private fun dataBase() = PenguinDataBase.getDataBase()
    private fun responseZoneDao() = dataBase().responseZoneDao()
    private fun responseStageDao() = dataBase().responseStageDao()

}