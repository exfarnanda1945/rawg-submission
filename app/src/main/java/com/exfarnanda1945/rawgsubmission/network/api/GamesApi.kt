package com.exfarnanda1945.rawgsubmission.network.api

import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {

    @GET("games")
    suspend fun getByDatesOrdering(
        @Query("page_size") pageSize: Int,
        @Query("dates") dates: String = "2022-01-01,2022-12-31",
        @Query("ordering") ordering: String = "-metacritic"
    ): Response<GameResponse>

    @GET("games")
    suspend fun getList(
        @Query("page_size") pageSize: Int
    ): Response<GameResponse>

    @GET("games")
    suspend fun searchGame(
        @Query("search") search: String
    ): Response<GameResponse>


}