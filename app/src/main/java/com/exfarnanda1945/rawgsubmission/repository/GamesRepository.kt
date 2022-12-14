package com.exfarnanda1945.rawgsubmission.repository

import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponse
import com.exfarnanda1945.rawgsubmission.network.api.GamesApi
import com.exfarnanda1945.rawgsubmission.network.handler.ApiHandler
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GamesRepository(private val api: GamesApi) {

    suspend fun getGameBestOfTheYear(pageSize: Int): Flow<ApiResponse<GameResponse>> = flow {
        emit(ApiHandler.call { api.getByDatesOrdering(pageSize) })
    }.flowOn(Dispatchers.IO)

    suspend fun getLatestGameRelease(pageSize: Int): Flow<ApiResponse<GameResponse>> = flow {
        emit(ApiHandler.call { api.getByDatesOrdering(pageSize, ordering = "-released") })
    }.flowOn(Dispatchers.IO)


    suspend fun getList(pageSize: Int): Flow<ApiResponse<GameResponse>> = flow {
        emit(ApiHandler.call { api.getList(pageSize) })
    }.flowOn(Dispatchers.IO)

    suspend fun search(search: String): Flow<ApiResponse<GameResponse>> = flow {
        emit(ApiHandler.call { api.searchGame(search) })
    }



}