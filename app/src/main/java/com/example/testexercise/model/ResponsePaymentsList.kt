package com.example.testapplication.api.response


import com.example.testexercise.model.ResponsePayments
import com.google.gson.annotations.SerializedName

data class ResponsePaymentsList(
    @SerializedName("response")
    val response: List<ResponsePayments>?,
    @SerializedName("success")
    val success: String,
    @SerializedName("error")
    val error: Error?,
)