package org.penguin_stats.android.network

import org.penguin_stats.android.data.ResponseItems
import org.penguin_stats.android.data.ResponseNotice
import org.penguin_stats.android.data.ResponseStages
import org.penguin_stats.android.data.ResponseTotalStats
import org.penguin_stats.android.data.ResponseZones
import org.penguin_stats.android.data.ResultMatrixNetwork
import org.penguin_stats.android.data.ResultPatternNetwork
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("PenguinStats/api/v2/notice")
    fun getNotice(): Call<List<ResponseNotice>>

    @GET("PenguinStats/api/v2/stats")
    fun getTotalStats(@Query("server") server: String): Call<ResponseTotalStats>

    @GET("PenguinStats/api/v2/zones")
    fun getZones(): Call<List<ResponseZones>>

    @GET("PenguinStats/api/v2/stages")
    fun getStages(): Call<List<ResponseStages>>

    @GET("PenguinStats/api/v2/items")
    fun getItems(): Call<List<ResponseItems>>

    @GET("PenguinStats/api/v2/result/pattern")
    fun getPattern(): Call<ResultPatternNetwork>

    @GET("PenguinStats/api/v2/result/pattern")
    fun getPatternDetailed(
        @Query("server") server: String,
        @Query("is_personal") isPersonal: Boolean,
        @Query("showAllPatterns") showAllPatterns: Boolean,
    ): Call<ResultPatternNetwork>

    @GET("PenguinStats/api/v2/result/matrix")
    fun getMatrix(): Call<ResultMatrixNetwork>

    @GET("PenguinStats/api/v2/result/matrix")
    fun getMatrixDetailed(
        @Query("server") server: String,
        @Query("is_personal") isPersonal: Boolean,
        @Query("show_closed_zones") showClosedZones: Boolean,
        @Query("category") category: String,
    ): Call<ResultMatrixNetwork>

}