package com.exfarnanda1945.rawgsubmission.network.api

import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenreApi {
    @GET("genres")
    suspend fun getList(): Response<BaseGameCountResponse>
}