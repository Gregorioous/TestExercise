package com.example.testexercise.repository

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.api.response.ResponseToken

interface NetworkRepository {
    suspend fun postLogin(requestToken: RequestToken): ResponseToken

    suspend fun getPayments(token: String): ResponsePaymentsList
}