package com.example.testexercise.data

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.data.api.UserApi
import com.example.testexercise.domain.repository.NetworkRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val apiService: UserApi,
) : NetworkRepository {

    override suspend fun postRegistration(requestToken: RequestToken): ResponseToken {
        return apiService.postToken(requestToken = requestToken)
    }

    override suspend fun getPayments(token: String): ResponsePaymentsList {
        return apiService.getPaymentsList(token = token)
    }
}