package com.example.testapplication.api.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("success")
    val success: String?,
    @SerializedName("token")
    val token: String?
)