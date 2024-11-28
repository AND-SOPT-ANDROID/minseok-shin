package org.sopt.and.data.repositoryimpl

import jakarta.inject.Inject
import org.sopt.and.data.datalocal.datasource.UserLocalDataSource
import org.sopt.and.data.dataremote.datasource.UserRemoteDataSource
import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.data.dataremote.model.response.ResponseMyHobbyDto
import org.sopt.and.data.dataremote.model.response.ResponseSignUpDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun postSignup(requestSignUpDto: RequestSignUpDto): Response<ResponseSignUpDto> =
        userRemoteDataSource.postSignup(requestSignUpDto)

    override suspend fun postLogin(requestLoginDto: RequestLoginDto): Response<ResponseLoginDto> =
        userRemoteDataSource.postLogin(requestLoginDto)

    override suspend fun getUserHobby(token: String): Response<ResponseMyHobbyDto> =
        userRemoteDataSource.getUserHobby(token)

    override fun saveAccessToken(token: String) {
        userLocalDataSource.accessToken = token
    }

    override fun getAccessToken(): String {
        return userLocalDataSource.accessToken
    }

    override fun saveNickname(nickname: String) {
        userLocalDataSource.nickname = nickname
    }

    override fun getNickname(): String {
        return userLocalDataSource.nickname
    }

    override fun clearUserData() {
        userLocalDataSource.clear()
    }
}