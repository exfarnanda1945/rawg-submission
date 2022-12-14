package com.exfarnanda1945.rawgsubmission.network.config

import com.exfarnanda1945.rawgsubmission.network.config.ApiConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request
            .url
            .newBuilder()
            .addQueryParameter("key", API_KEY)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(url)
                .build()
        )
    }
}