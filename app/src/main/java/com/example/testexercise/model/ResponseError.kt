package com.example.testapplication.api.response


import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("error")
    val error: Error?,
    @SerializedName("success")
    val success: String?
)

data class Error(
    @SerializedName("error_code")
    val errorCode: Int?,
    @SerializedName("error_msg")
    val errorMsg: String?
)