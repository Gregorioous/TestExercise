package com.example.testexercise.domain

import com.example.testexercise.domain.usecase.GetPaymentsUseCase
import com.example.testexercise.domain.usecase.PostRegistrationUseCase

data class UseCase(
    val postRegistrationUseCase: PostRegistrationUseCase,
    val getPaymentsUseCase: GetPaymentsUseCase,
)