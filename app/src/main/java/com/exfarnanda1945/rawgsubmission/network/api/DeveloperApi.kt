package com.exfarnanda1945.rawgsubmission.network.api

import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DeveloperApi {
    @GET("developers")
    suspend fun getList(
        @Query("page_size") pageSize: Int,
        @Query("search") search: String
    ): Response<BaseGameCountResponse>
}