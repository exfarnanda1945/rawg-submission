package com.exfarnanda1945.rawgsubmission.repository

import com.exfarnanda1945.rawgsubmission.model.game_screenshots.GameScreenshotsResponse
import com.exfarnanda1945.rawgsubmission.network.api.GamesScreenshotsApi
import com.exfarnanda1945.rawgsubmission.network.handler.ApiHandler
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse

class GameScreenshotsRepository(private val api:GamesScreenshotsApi) {
    suspend fun getScreenshots(gameId: Int): ApiResponse<GameScreenshotsResponse> =
        ApiHandler.call { api.getScreenshots(gameId) }

}