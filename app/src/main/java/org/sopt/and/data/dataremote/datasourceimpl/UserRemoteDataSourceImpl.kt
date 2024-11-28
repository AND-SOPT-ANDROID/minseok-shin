package org.sopt.and.data.dataremote.datasourceimpl

import org.sopt.and.data.dataremote.datasource.UserRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.data.dataremote.model.response.ResponseMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignUpDto
import org.sopt.and.data.dataremote.service.UserService
import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val service: UserService
) : UserRemoteDataSource {
    override suspend fun postSignup(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto> =
        service.postSignup(requestSignUpDto)

    override suspend fun postLogin(requestLoginDto: RequestLoginDto): Response<ResponseLoginDto> =
        service.postLogin(requestLoginDto)

    override suspend fun getUserHobby(token: String): Response<ResponseMyHobbyDto> =
        service.getUserHobby(token)
}