package com.example.testapplication.api.response

import com.example.testexercise.data.api.model.Exception
import com.google.gson.annotations.SerializedName

data class ResponseToken(
    @SerializedName("response")
    val response: Response?,
    @SerializedName("success")
    val success: String,
    @SerializedName("error")
    val error: Exception?,
)
