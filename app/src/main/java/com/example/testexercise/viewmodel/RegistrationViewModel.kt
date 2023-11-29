package com.example.testexercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.testapplication.api.request.RequestToken
import com.example.testexercise.api.AuthInterceptor
import com.example.testexercise.repository.RegistrationRepository
import com.example.testexercise.utills.BaseResponse
import kotlinx.coroutines.Dispatchers

class RegistrationViewModel(private val loginRepository: RegistrationRepository) : ViewModel() {

    fun getToken(login: String, password: String) = liveData(Dispatchers.IO) {
        emit(BaseResponse.Loading)

        val requestToken = RequestToken(login, password)
        emit(loginRepository.getAccessToken(requestToken))
    }

    fun setToken(token: String?) {
        AuthInterceptor.token = token
    }
}