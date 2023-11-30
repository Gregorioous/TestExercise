package com.example.testexercise.domain.usecase

import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testexercise.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPaymentsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
) {

    suspend operator fun invoke(token: String): ResponsePaymentsList {
        return withContext(Dispatchers.IO) {
            networkRepository.getPayments(token)
        }
    }
}
