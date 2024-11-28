package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(requestLoginDto: RequestLoginDto): Response<ResponseLoginDto> =
        userRepository.postLogin(requestLoginDto = requestLoginDto)
}