package org.sopt.and.domain.repository

import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.domain.model.Hobby
import org.sopt.and.domain.model.Token

interface UserRepository {
    suspend fun postSignup(requestSignUpDto: RequestSignUpDto): Result<Unit>

    suspend fun postLogin(requestLoginDto: RequestLoginDto): Result<Token>

    suspend fun getUserHobby(token: String): Result<Hobby>
}