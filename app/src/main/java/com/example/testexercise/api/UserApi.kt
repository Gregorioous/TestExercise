package com.example.testexercise.api

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.api.response.ResponseToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @POST("login")
    suspend fun getToken(@Body requestToken: RequestToken): ResponseToken

    @GET("payments")
    suspend fun getPaymentsList(): ResponsePaymentsList

}