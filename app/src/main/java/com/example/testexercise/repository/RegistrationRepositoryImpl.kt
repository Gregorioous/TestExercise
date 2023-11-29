package com.example.testexercise.repository

import com.example.testapplication.api.request.RequestToken
import com.example.testapplication.api.response.ResponseToken
import com.example.testexercise.api.UserApi
import com.example.testexercise.utills.BaseRepository
import com.example.testexercise.utills.BaseResponse

class RegistrationRepositoryImpl(private val networkService: UserApi) : BaseRepository(),
    RegistrationRepository {
    override suspend fun getAccessToken(requestToken: RequestToken): BaseResponse<ResponseToken> =
        getActionResult { networkService.getToken(requestToken) }
}