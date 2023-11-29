package com.example.testexercise.repository

import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testexercise.api.UserApi
import com.example.testexercise.utills.BaseRepository
import com.example.testexercise.utills.BaseResponse

class PaymentsRepositoryImpl(private val networkService: UserApi) : BaseRepository(),
    PaymentsRepository {
    override suspend fun getPaymentsList(): BaseResponse<ResponsePaymentsList> =
        getActionResult { networkService.getPaymentsList() }
}
