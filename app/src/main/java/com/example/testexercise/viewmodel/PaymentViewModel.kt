package com.example.testexercise.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testexercise.model.ResponsePayments
import com.example.testexercise.repository.NetworkRepository
import com.example.testexercise.utills.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentsViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val responsePayments = MutableLiveData<Resource<ResponsePayments>>()

    fun getUsersPayments(usersToken: String) {
        viewModelScope.launch {
            try {
                responsePayments.postValue(Resource.Loading())
                val paymentsList = networkRepository.getPayments(usersToken)
                //     responsePayments.postValue(Resource.Success(paymentsList))
            } catch (e: Exception) {
                responsePayments.postValue(Resource.Error(e.message!!))
            }
        }
    }
}