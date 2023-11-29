package com.example.testexercise.api

import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("app-key", "12345")
                .addHeader("v", "1")
                .build()
        )
    }
}