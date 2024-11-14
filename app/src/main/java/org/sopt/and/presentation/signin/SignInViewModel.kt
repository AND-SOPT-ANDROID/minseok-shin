package org.sopt.and.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.model.request.RequestLoginDto
import org.sopt.and.data.model.response.ResponseFailedDto
import org.sopt.and.data.service.RetrofitInstance.userService
import org.sopt.and.domain.User

class SignInViewModel(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _isLoginSuccessful = MutableStateFlow<Boolean?>(null)
    val isLoginSuccessful = _isLoginSuccessful.asStateFlow()

    fun onEmailChanged(email: String) {
        _user.value = _user.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _user.value = _user.value.copy(password = password)
    }


    suspend fun signIn() {
        val requestDto = RequestLoginDto(
            username = _user.value.email,
            password = _user.value.password
        )

        try {
            val response = userService.postLogin(requestDto)
            val token = response.body()?.result?.token

            if (response.isSuccessful && token != null) {
                userInfoLocalDataSource.accessToken = token
                userInfoLocalDataSource.nickname = _user.value.email
                _isLoginSuccessful.value = true
                Log.d(
                    "ㅋㅋ",
                    "Status code: ${response.code()} token: $token"
                )
            } else {
                val errorBody = response.errorBody()?.string()
                val errorCode = if (errorBody != null) {
                    val errorData = Json.decodeFromString<ResponseFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }
                Log.e("ㅋㅋ", "Status code: ${response.code()} and error code: $errorCode")
                _isLoginSuccessful.value = false
            }
        } catch (e: Exception) {
            Log.e("ㅋㅋ", "Exception 이지롱: ${e.message}")
            _isLoginSuccessful.value = false
        }
    }
}
