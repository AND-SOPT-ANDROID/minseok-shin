package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(requestSignUpDto: RequestSignUpDto): Result<Unit> =
        userRepository.postSignup(requestSignUpDto = requestSignUpDto)
}
