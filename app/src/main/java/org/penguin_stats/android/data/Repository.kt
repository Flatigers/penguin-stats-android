package org.penguin_stats.android.data

import android.util.Log
import kotlinx.coroutines.coroutineScope
import org.penguin_stats.android.network.Network
import org.penguin_stats.android.util.genSplitNum

object Repository {

    fun isNoticeSaved() = ResponseDao.isNoticeSaved()
    fun isStatsUISaved() = ViewDao.isStatsUISaved()

    suspend fun readNotice() = coroutineScope {
        ResponseDao.readNotice()
    }

    suspend fun readStatsUI() = coroutineScope {
        ViewDao.readStatsUI()
    }

    suspend fun readAllZones() = coroutineScope {
        responseZonesDao().loadAllZones()
    }

    suspend fun readZonesByType(type: String) = coroutineScope {
        responseZonesDao().loadZonesByType(type)
    }

    suspend fun readZoneById(id: String) = coroutineScope {
        responseZonesDao().loadZoneById(id)
    }

    suspend fun readAllStages() = coroutineScope {
        responseStagesDao().loadAllStages()
    }

    suspend fun readStageById(id: String) = coroutineScope {
        responseStagesDao().loadStageByStageId(id)
    }

    suspend fun readAllItems() = coroutineScope {
        responseItemsDao().loadAllItems()
    }

    suspend fun readItemById(id: String) = coroutineScope {
        responseItemsDao().loadItemById(id)
    }

    suspend fun readAllPatterns() = coroutineScope {
        resultPatternDao().loadAllPatterns()
    }

    suspend fun readPatternsByStageId(stageId: String) = coroutineScope {
        resultPatternDao().loadPatternsByStageId(stageId)
    }

    suspend fun readAllMatrix() = coroutineScope {
        resultMatrixDao().loadAllMatrix()
    }


    suspend fun saveNotice() = coroutineScope {
        try {
            val response = Network.getNotice()
            ResponseDao.saveNotice(response)
        } catch (e: Exception) {
            Log.e("E-repo: Notice", e.toString())
        }
    }

    suspend fun saveStats() = coroutineScope {
        try {
            val response = Network.getTotalStats()
            ResponseDao.saveStats(response)
            val statsUI = TotalStatsUI(
                response.totalApCost.genSplitNum(),
                response.totalReports.sumOf { it.times }.genSplitNum(),
                response.totalItemQuantities.sumOf { it.quantity }.genSplitNum()
            )
            ViewDao.saveStatsUI(statsUI)
        } catch (e: Exception) {
            Log.e("E-repo: Stats", e.toString())
        }
    }

    suspend fun saveZones() = coroutineScope {
        try {
            val response = Network.getZones()
            responseZonesDao().insertAllZones(response)
        } catch (e: Exception) {
            Log.e("E-repo: Zones", e.toString())
        }
    }

    suspend fun saveStages() = coroutineScope {
        try {
            val response = Network.getStages()
            responseStagesDao().insertAllStages(response)
        } catch (e: Exception) {
            Log.e("E-repo: Stages", e.toString())
        }
    }

    suspend fun saveItems() = coroutineScope {
        try {
            val response = Network.getItems()
            responseItemsDao().insertAllItems(response)
        } catch (e: Exception) {
            Log.e("E-repo: Items", e.toString())
        }
    }

    suspend fun savePatterns() = coroutineScope {
        try {
            val response = Network.getPattern()
            val pattern = response.patternMatrix
            resultPatternDao().insertAllPatterns(pattern)
        } catch (e: Exception) {
            Log.e("E-repo: Patterns", e.toString())
        }
    }

    suspend fun saveMatrix() = coroutineScope {
        try {
            val response = Network.getMatrix()
            val matrix = response.matrix
            resultMatrixDao().insertAllMatrix(matrix)
        } catch (e: Exception) {
            Log.e("E-repo: Matrix", e.toString())
        }
    }


    private fun dataBase() = PenguinDataBase.getDataBase()
    private fun responseZonesDao() = dataBase().responseZoneDao()
    private fun responseStagesDao() = dataBase().responseStageDao()
    private fun responseItemsDao() = dataBase().responseItemsDao()
    private fun resultPatternDao() = dataBase().resultPatternDao()
    private fun resultMatrixDao() = dataBase().resultMatrixDao()
}