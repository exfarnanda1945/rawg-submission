package com.exfarnanda1945.rawgsubmission.network.config

import com.exfarnanda1945.rawgsubmission.network.route.GamesRoute
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    private val client = OkHttpClient
        .Builder()
        .addInterceptor(ApiInterceptor())
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val GameService:GamesRoute by lazy {
        retrofit.create(GamesRoute::class.java)
    }
}