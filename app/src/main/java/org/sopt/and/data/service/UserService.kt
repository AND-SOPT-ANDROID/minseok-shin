package org.sopt.and.data.service

import org.sopt.and.data.model.request.RequestLoginDto
import org.sopt.and.data.model.request.RequestSignUpDto
import org.sopt.and.data.model.response.ResponseLoginDto
import org.sopt.and.data.model.response.ResponseSignUpDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserService {
    @POST("/user")
    suspend fun postSignup(@Body requestDto: RequestSignUpDto): Response<ResponseSignUpDto>

    @POST("/login")
    suspend fun postLogin(@Body requestDto: RequestLoginDto): Response<ResponseLoginDto>
}