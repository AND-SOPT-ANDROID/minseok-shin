package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestLoginDto
import org.sopt.and.domain.model.Token
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(requestLoginDto: RequestLoginDto): Result<Token> =
        userRepository.postLogin(requestLoginDto = requestLoginDto)
}
