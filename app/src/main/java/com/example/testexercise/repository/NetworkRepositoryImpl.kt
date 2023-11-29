package com.example.testexercise.repository

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.api.UserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val apiService: UserApi,
) : NetworkRepository {

    override suspend fun postLogin(requestToken: RequestToken): ResponseToken {
        return apiService.postToken(requestToken = requestToken)
    }

    override suspend fun getPayments(token: String): ResponsePaymentsList {
        return apiService.getPaymentsList(token = token)
    }
}