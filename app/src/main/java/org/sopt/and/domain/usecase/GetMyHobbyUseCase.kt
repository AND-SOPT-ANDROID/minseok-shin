package org.sopt.and.domain.usecase

import org.sopt.and.domain.model.Hobby
import org.sopt.and.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyHobbyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): Result<Hobby> =
        userRepository.getUserHobby(token = token)
}
