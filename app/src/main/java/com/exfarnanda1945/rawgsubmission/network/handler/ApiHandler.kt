package com.exfarnanda1945.rawgsubmission.network.handler

import retrofit2.Response

object ApiHandler {
    inline fun <T> call(apiCall: () -> Response<T>): ApiResponse<T> {
        return try {
            ApiResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            ApiResponse.failure(e)
        }
    }
}