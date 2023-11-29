package com.example.testexercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testexercise.api.AuthInterceptor
import com.example.testexercise.repository.PaymentsRepository
import com.example.testexercise.utills.BaseResponse
import kotlinx.coroutines.Dispatchers

class PaymentsViewModel(private val paymentsRepository: PaymentsRepository) : ViewModel() {

    fun getPaymentsList() = liveData(Dispatchers.IO) {
        emit(BaseResponse.Loading)

        emit(paymentsRepository.getPaymentsList())
    }

    fun setNullToToken() {
        AuthInterceptor.token = null
    }
}