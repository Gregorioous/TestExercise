package com.example.testexercise.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.api.response.ResponsePaymentsList
import com.example.testexercise.domain.UseCase
import com.example.testexercise.utills.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val useCase: UseCase,
) : ViewModel() {

    val responsePayments = MutableLiveData<Resource<ResponsePaymentsList>>()


    fun getUsersPayments(usersToken: String) {
        viewModelScope.launch {
            try {
                responsePayments.postValue(Resource.Loading())
                val paymentsList = useCase.getPaymentsUseCase.invoke(usersToken)
                responsePayments.postValue(Resource.Success(paymentsList))
            } catch (e: Exception) {
                responsePayments.postValue(Resource.Error(e.message!!))
            }
        }
    }
}