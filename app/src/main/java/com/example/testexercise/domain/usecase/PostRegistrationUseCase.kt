package com.example.testexercise.domain.usecase

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRegistrationUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
) {

    suspend operator fun invoke(requestToken: RequestToken): ResponseToken {
        return withContext(Dispatchers.IO) {
            networkRepository.postRegistration(requestToken = requestToken)
        }
    }
}