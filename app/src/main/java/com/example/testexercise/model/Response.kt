package com.example.testapplication.api.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("token")
    val token: String?
)