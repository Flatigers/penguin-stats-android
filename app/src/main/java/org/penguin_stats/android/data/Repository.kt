package org.penguin_stats.android.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.penguin_stats.android.network.Network
import org.penguin_stats.android.util.genSplitNum

object Repository {

    fun isNoticeSaved() = ResponseDao.isNoticeSaved()
    fun isStatsSaved() = ResponseDao.isStatsSaved()
    fun isZonesSaved() = ResponseDao.isZonesSaved()
    fun isStagesSaved() = ResponseDao.isStagesSaved()
    fun isStatsUISaved() = ViewDao.isStatsUISaved()
    fun isZonesUISaved() = ViewDao.isZonesUISaved()
    fun isStagesUISaved() = ViewDao.isStagesUISaved()

    fun readStatsUI() = ViewDao.readStatsUI()
    fun readZonesUI() = ViewDao.readZonesUI()
    fun readStagesUI() = ViewDao.readStagesUI()

    fun readNotice() = ResponseDao.readNotice()
    fun readStats() = ResponseDao.readStats()
    fun readZones() = ResponseDao.readZones()
    fun readStages() = ResponseDao.readStages()

    suspend fun saveNotice() = coroutineScope {
        runBlocking {
            try {
                withContext(Dispatchers.IO) {
                    val response = Network.getNotice()
                    ResponseDao.saveNotice(response)
                }
            } catch (e: Exception) {
                Log.e("E-repo: Notice", e.toString())
            }
        }
    }

    suspend fun saveStats() = coroutineScope {
        runBlocking {
            try {
                withContext(Dispatchers.IO) {
                    val response = Network.getTotalStats()
                    ResponseDao.saveStats(response)
                    val statsUI = TotalStatsUI(
                        genSplitNum(response.totalApCost),
                        genSplitNum(response.totalReports.sumOf { it.times }),
                        genSplitNum(response.totalItemQuantities.sumOf { it.quantity })
                    )
                    ViewDao.saveStatsUI(statsUI)
                }
            } catch (e: Exception) {
                Log.e("E-repo: Stats", e.toString())
            }
        }
    }

    suspend fun saveZones() = coroutineScope {
        runBlocking {
            try {
                withContext(Dispatchers.IO) {
                    val response = Network.getZones()
                    ResponseDao.saveZones(response)
                    val zoneUI = mutableListOf<ZoneUI>()
                    response.forEach {
                        zoneUI.add(
                            ZoneUI(
                                it.zoneId,
                                it.zoneName_i18n,
                                it.existence,
                                it.stages
                            )
                        )
                    }
                    ViewDao.saveZonesUI(zoneUI)
                }
            } catch (e: Exception) {
                Log.e("E-repo: Zones", e.toString())
            }
        }
    }

    suspend fun saveStages() = coroutineScope {
        runBlocking {
            try {
                withContext(Dispatchers.IO) {
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
                }
            } catch (e: Exception) {
                Log.e("E-repo: Stages", e.toString())
            }
        }
    }

}