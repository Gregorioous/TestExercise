package com.example.testapplication.api.request

import com.google.gson.annotations.SerializedName

data class RequestToken(
    @SerializedName("login")
    val login: String,

    @SerializedName("password")
    val password: String
)
