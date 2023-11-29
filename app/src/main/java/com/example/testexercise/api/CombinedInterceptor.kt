package com.example.testexercise.api

import okhttp3.Interceptor
import okhttp3.Response


class CombinedInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // Добавляем общие заголовки
        val requestBuilder = original.newBuilder()
            .addHeader("app-key", "12345")
            .addHeader("v", "1")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
