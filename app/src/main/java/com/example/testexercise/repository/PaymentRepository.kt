package com.example.testexercise.repository

import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testexercise.utills.BaseResponse

interface PaymentsRepository {
    suspend fun getPaymentsList(): BaseResponse<ResponsePaymentsList>
}