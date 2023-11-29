package com.example.testexercise.utills

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(val responseData: T) : Resource<T>(data = responseData)
    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)
    class Loading<T> : Resource<T>()
}
