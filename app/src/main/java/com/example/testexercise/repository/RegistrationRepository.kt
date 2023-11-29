package com.example.testexercise.repository

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.utills.BaseResponse

interface RegistrationRepository {
    suspend fun getAccessToken(requestToken: RequestToken): BaseResponse<ResponseToken>
}
