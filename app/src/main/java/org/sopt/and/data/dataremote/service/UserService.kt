package org.sopt.and.data.dataremote.service

import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.data.dataremote.model.response.ResponseMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface UserService {
    @POST("/user")
    suspend fun postSignup(@Body requestSignUpDto: RequestSignUpDto): ResponseSignUpDto

    @POST("/login")
    suspend fun postLogin(@Body requestLoginDto: RequestLoginDto): ResponseLoginDto


    @GET("/user/my-hobby")
    suspend fun getUserHobby(
        @Header("token") token: String
    ): ResponseMyHobbyDto

}