package com.exfarnanda1945.rawgsubmission.network.config

import com.exfarnanda1945.rawgsubmission.network.api.*
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

    val GameService:GamesApi by lazy {
        retrofit.create(GamesApi::class.java)
    }
    val GameDetailService:GamesDetailApi by lazy{
        retrofit.create(GamesDetailApi::class.java)
    }

    val GameScreenshotsService:GamesScreenshotsApi by lazy {
        retrofit.create(GamesScreenshotsApi::class.java)
    }

    val GenreService:GenreApi by lazy {
        retrofit.create(GenreApi::class.java)
    }

    val DeveloperService:DeveloperApi by lazy{
        retrofit.create(DeveloperApi::class.java)
    }

    val PublisherService:PublisherApi by lazy{
        retrofit.create(PublisherApi::class.java)
    }
}