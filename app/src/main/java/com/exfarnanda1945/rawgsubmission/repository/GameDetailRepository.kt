package com.exfarnanda1945.rawgsubmission.repository

import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailResponse
import com.exfarnanda1945.rawgsubmission.network.api.GamesDetailApi
import com.exfarnanda1945.rawgsubmission.network.handler.ApiHandler
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse

class GameDetailRepository(private val api: GamesDetailApi) {
    suspend fun getDetail(gameId: Int): ApiResponse<GameDetailResponse> =
        ApiHandler.call { api.getDetail(gameId) }
}