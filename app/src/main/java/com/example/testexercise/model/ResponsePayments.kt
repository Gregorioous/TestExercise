package com.example.testexercise.model

import com.google.gson.annotations.SerializedName

data class ResponsePayments(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("amount")
    val amount: Any?,
    @SerializedName("created")
    val created: Long?
)
