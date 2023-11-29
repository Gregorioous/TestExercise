package com.example.testexercise

import android.app.Application
import com.example.testexercise.api.CombinedInterceptor
import com.example.testexercise.api.UserApi
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class App : Application() {


    private fun provideRetrofit(): Retrofit {
        val combinedInterceptor = CombinedInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(combinedInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://easypay.world/api-test/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun provideNetworkApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

}