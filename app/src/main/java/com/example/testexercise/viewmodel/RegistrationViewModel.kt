package com.example.testexercise.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.domain.UseCase
import com.example.testexercise.utills.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val useCase: UseCase,
) : ViewModel() {

    val loginResponseToken = MutableLiveData<Resource<ResponseToken>>()

    fun login(userLogin: String, userPassword: String) {
        viewModelScope.launch {
            try {
                loginResponseToken.postValue(Resource.Loading())
                val responseToken =
                    useCase.postRegistrationUseCase.invoke(RequestToken(userLogin, userPassword))
                loginResponseToken.postValue(Resource.Success(responseToken))
            } catch (e: Exception) {
                loginResponseToken.postValue(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }
}