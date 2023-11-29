package com.example.testexercise.utills

sealed class BaseResponse<out RESPONSE> {

    data class Success<RESPONSE>(val response: RESPONSE) : BaseResponse<RESPONSE>()
    data class Failed(val error: String) : BaseResponse<Nothing>()
    object Loading : BaseResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[response = $response]"
            is Failed -> "Failed[error = $error]"
            is Loading -> "Loading"
        }
    }
}
