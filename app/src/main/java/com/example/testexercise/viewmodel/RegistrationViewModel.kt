package com.example.testexercise.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.repository.NetworkRepository
import com.example.testexercise.utills.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val loginResponseToken = MutableLiveData<Resource<ResponseToken>>()

    fun login(userLogin: String, userPassword: String) {
        viewModelScope.launch {
            try {
                loginResponseToken.postValue(Resource.Loading())

                val responseToken =
                    networkRepository.postLogin(RequestToken(userLogin, userPassword))

                loginResponseToken.postValue(Resource.Success(responseToken))
            } catch (e: Exception) {
                loginResponseToken.postValue(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }
}