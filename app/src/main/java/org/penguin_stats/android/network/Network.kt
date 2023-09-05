package org.penguin_stats.android.network

import org.penguin_stats.android.app.AppConfig
import penguin_stats.roguelike.TestQuery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object Network {
    private val penguinService = ServiceCreator.create<NetworkService>()
    private val apolloClient = ServiceCreator.createApollo()

    suspend fun getTotalStats() = penguinService.getTotalStats(
        AppConfig.getServer()
    ).await()

    suspend fun getNotice() = penguinService.getNotice().await()
    suspend fun getZones() = penguinService.getZones().await()
    suspend fun getStages() = penguinService.getStages().await()
    suspend fun getItems() = penguinService.getItems().await()

    suspend fun getPattern() = penguinService.getPattern().await()
    suspend fun getMatrix() = penguinService.getMatrix().await()
    suspend fun getPatternDetailed(
        server: String, isPersonal: Boolean, showAllPatterns: Boolean,
    ) = penguinService.getPatternDetailed(server, isPersonal, showAllPatterns)

    suspend fun getMatrixDetailed(
        server: String, isPersonal: Boolean, showClosed: Boolean, category: String,
    ) = penguinService.getMatrixDetailed(server, isPersonal, showClosed, category)

    suspend fun getRogueTest() = apolloClient.query(TestQuery()).execute()


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(
                            RuntimeException("Null Response")
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}