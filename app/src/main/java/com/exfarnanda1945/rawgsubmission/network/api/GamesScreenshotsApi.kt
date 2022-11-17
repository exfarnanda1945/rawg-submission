package com.exfarnanda1945.rawgsubmission.network.api

import com.exfarnanda1945.rawgsubmission.model.game_screenshots.GameScreenshotsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GamesScreenshotsApi {

    @GET("games/{gameId}/screenshots")
    suspend fun getScreenshots(
        @Path("gameId") gameId:Int
    ): Response<GameScreenshotsResponse>
}