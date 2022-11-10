package com.exfarnanda1945.rawgsubmission.utils

import android.content.Context
import android.widget.Toast
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse

object HandlerApiClient {
    fun <T> handle(response: ApiResponse<T>, context: Context, callBack: HandlerApiClientCallBack) {
        when (response.status) {
            ApiResponse.Status.Success -> {
                val responseData = response.data
                when (responseData?.code()) {
                    200 -> callBack.onGetDataSuccess()
                    else -> {
                        Toast.makeText(
                            context,
                            "Code ${responseData?.code()} ${responseData?.message()}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        callBack.onGetDataFailed()
                    }
                }
            }
            ApiResponse.Status.Loading -> callBack.onApiResponseLoading()
            else -> {
                Toast.makeText(
                    context,
                    response.exception?.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
                callBack.onApiResponseFailed()
            }
        }

    }

    interface HandlerApiClientCallBack {
        fun onApiResponseLoading()
        fun onApiResponseFailed()
        fun onGetDataSuccess()
        fun onGetDataFailed()
    }
}