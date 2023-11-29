package com.example.testexercise.api

import com.example.testexercise.utills.PreferenseManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(var preferencesManager: PreferenseManager) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val token = preferencesManager.token

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("token", token)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}