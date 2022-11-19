package com.exfarnanda1945.rawgsubmission.repository

import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import com.exfarnanda1945.rawgsubmission.network.api.DeveloperApi
import com.exfarnanda1945.rawgsubmission.network.handler.ApiHandler
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeveloperRepository(private val api: DeveloperApi) {
    suspend fun getList(pageSize: Int, search: String): Flow<ApiResponse<BaseGameCountResponse>> =
        flow {
            emit(ApiHandler.call { api.getList(pageSize, search) })
        }.flowOn(Dispatchers.IO)
}