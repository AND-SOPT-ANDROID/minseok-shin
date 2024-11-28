package org.sopt.and.domain.usecase

import org.sopt.and.data.dataremote.model.response.ResponseMyHobbyDto
import org.sopt.and.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyHobbyUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): Response<ResponseMyHobbyDto> =
        userRepository.getUserHobby(token = token)
}
