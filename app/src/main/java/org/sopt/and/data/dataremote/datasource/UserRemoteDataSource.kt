package org.sopt.and.data.dataremote.datasource

import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.data.dataremote.model.response.ResponseMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignUpDto
import retrofit2.Response


interface UserRemoteDataSource {
    suspend fun postSignup(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto>

    suspend fun postLogin(requestLoginDto: RequestLoginDto): Response<ResponseLoginDto>

    suspend fun getUserHobby(token: String): Response<ResponseMyHobbyDto>
}
