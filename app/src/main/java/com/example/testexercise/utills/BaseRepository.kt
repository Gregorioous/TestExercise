package com.example.testexercise.utills

import com.example.testapplication.api.response.ResponseBaseBody

abstract class BaseRepository() {

    protected suspend fun <RESPONSE : ResponseBaseBody> getActionResult(call: suspend () -> RESPONSE): BaseResponse<RESPONSE> {
        return try {
            val response = call.invoke()
            return if (response.success == "true") {
                BaseResponse.Success(response)
            } else {
                val errorCode = response.error?.error?.errorMsg ?: "null"
                BaseResponse.Failed(errorCode)
            }
        } catch (exception: java.lang.Exception) {
            BaseResponse.Failed("Network error")
        }
    }

}