package org.sopt.and.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.data.model.request.RequestSignUpDto
import org.sopt.and.data.model.response.ResponseFailedDto
import org.sopt.and.data.service.RetrofitInstance
import org.sopt.and.domain.User


class SignUpViewModel : ViewModel() {
    private val userService = RetrofitInstance.userService
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user
    private val _buttonClickable = MutableStateFlow(false)
    val buttonClickable: StateFlow<Boolean> = _buttonClickable
    private val _signUpResult = MutableStateFlow<Result<Unit>?>(null)
    val signUpResult: StateFlow<Result<Unit>?> = _signUpResult

    fun onEmailChanged(email: String) {
        _user.value = _user.value.copy(email = email)
        validateButtonState(email, _user.value.password, _user.value.hobby)
    }

    fun onPasswordChanged(password: String) {
        _user.value = _user.value.copy(password = password)
        validateButtonState(_user.value.email, password, _user.value.hobby)
    }

    fun onHobbyChanged(hobby: String) {
        _user.value = _user.value.copy(hobby = hobby)
        validateButtonState(_user.value.email, _user.value.password, hobby)
    }

    private fun validateButtonState(email: String, password: String, hobby: String) {
        _buttonClickable.value =
            isValidEmail(email) && isValidPassword(password) && isValidateHobby(hobby)
    }

    suspend fun signUp() {
        val requestDto = RequestSignUpDto(
            username = _user.value.email,
            password = _user.value.password,
            hobby = _user.value.hobby
        )

        try {
            val response = userService.postSignup(requestDto)
            if (response.isSuccessful) {
                _signUpResult.value = Result.success(Unit)
                Log.d("ㅋㅋ", "Status code: ${response.code()}")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorCode = if (errorBody != null) {
                    val errorData = Json.decodeFromString<ResponseFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }
                _signUpResult.value =
                    Result.failure(Exception("Status code: ${response.code()} and error code: $errorCode"))
            }
        } catch (e: Exception) {
            Log.e("ㅋㅋ", "Exception 이지롱: ${e.message}")
            _signUpResult.value = Result.failure(e)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.length in 1..8
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length in 1..8
    }

    private fun isValidateHobby(hobby: String): Boolean {
        return hobby.length in 1..8
    }
}

