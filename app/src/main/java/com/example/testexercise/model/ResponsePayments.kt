package com.example.testapplication.api.response


import com.google.gson.annotations.SerializedName

data class ResponsePaymentsList(
    @SerializedName("response")
    val responsePayments: List<ResponsePayments>
) : ResponseBaseBody() {
    data class ResponsePayments(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String?,
        @SerializedName("amount")
        val amount: String?,
        @SerializedName("created")
        val created: Long?
    )
}