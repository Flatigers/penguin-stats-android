package org.penguin_stats.android.network

import com.apollographql.apollo3.ApolloClient
import org.penguin_stats.android.app.AppConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private val BaseUrl = "https://penguin-stats.${AppConfig.getDomain()}/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(BaseUrl)
        .build()

    fun createApollo() = apolloClient

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}