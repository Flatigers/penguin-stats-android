package org.penguin_stats.android.network

import org.penguin_stats.android.data.ResponseNotice
import org.penguin_stats.android.data.ResponseStages
import org.penguin_stats.android.data.ResponseTotalStats
import org.penguin_stats.android.data.ResponseZones
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
}