package com.exfarnanda1945.rawgsubmission.repository

import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import com.exfarnanda1945.rawgsubmission.network.api.GenreApi
import com.exfarnanda1945.rawgsubmission.network.handler.ApiHandler
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GenreRepository(private val api: GenreApi) {
    suspend fun getList(): Flow<ApiResponse<BaseGameCountResponse>> = flow {
        emit(ApiHandler.call { api.getList() })
    }.flowOn(Dispatchers.IO)
}