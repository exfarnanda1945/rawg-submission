package com.exfarnanda1945.rawgsubmission.network.api

import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GamesDetailApi {
    @GET("games/{gameId}")
    suspend fun getDetail(
        @Path("gameId") gameId:Int
    ):Response<GameDetailResponse>

}