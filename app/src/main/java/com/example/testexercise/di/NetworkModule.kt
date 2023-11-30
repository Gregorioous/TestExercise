package com.example.testexercise.di

import com.example.testexercise.data.api.CombinedInterceptor
import com.example.testexercise.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
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
}