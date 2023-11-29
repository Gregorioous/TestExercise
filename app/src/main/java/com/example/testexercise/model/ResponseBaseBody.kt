package com.example.testapplication.api.response


import com.google.gson.annotations.SerializedName

open class ResponseBaseBody(
    @SerializedName("success")
    val success: String? = null, // non valid json syntax. need to be true instead of "true"

    @SerializedName("error")
    val error: ResponseError? = null
)
