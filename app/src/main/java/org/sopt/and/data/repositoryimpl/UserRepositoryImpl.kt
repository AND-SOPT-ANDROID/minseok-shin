package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.dataremote.datasource.UserRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.data.mapper.todomain.toDomain
import org.sopt.and.domain.model.Hobby
import org.sopt.and.domain.model.Token
import org.sopt.and.domain.repository.UserRepository

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun postSignup(requestSignUpDto: RequestSignUpDto): Result<Unit> =
        runCatching {
            userRemoteDataSource.postSignup(requestSignUpDto = requestSignUpDto)
        }

    override suspend fun postLogin(requestLoginDto: RequestLoginDto): Result<Token> = runCatching {
        userRemoteDataSource.postLogin(requestLoginDto = requestLoginDto).toDomain()
    }

    override suspend fun getUserHobby(token: String): Result<Hobby> = runCatching {
        userRemoteDataSource.getUserHobby(token = token).toDomain()
    }
}