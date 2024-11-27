package org.sopt.and.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.data.model.request.RequestSignUpDto
import org.sopt.and.data.model.response.ResponseFailedDto
import org.sopt.and.data.service.RetrofitInstance
import org.sopt.and.domain.User
import retrofit2.HttpException
import java.io.IOException


class SignUpViewModel : ViewModel() {
    private val userService = RetrofitInstance.userService

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _buttonClickable = MutableStateFlow(false)
    val buttonClickable: StateFlow<Boolean> = _buttonClickable

    private val _signUpEvent = MutableSharedFlow<Result<Unit>>()
    val signUpEvent: SharedFlow<Result<Unit>> = _signUpEvent

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
            isValidEmail(email) && isValidPassword(password) && isValidHobby(hobby)
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
                _signUpEvent.emit(Result.success(Unit))
                Log.d("ㅋㅋ", "Status code: ${response.code()}")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorCode = if (errorBody != null) {
                    val errorData = Json.decodeFromString<ResponseFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }
                _signUpEvent.emit(
                    Result.failure(Exception("회원가입 실패: Status code ${response.code()}, Error code: $errorCode"))
                )
            }
        } catch (e: IOException) {
            Log.e("SignUpViewModel", "네트워크 오류: ${e.message}")
            _signUpEvent.emit(Result.failure(Exception("네트워크 오류: ${e.message}")))
        } catch (e: HttpException) {
            Log.e("SignUpViewModel", "서버 오류: ${e.message}")
            _signUpEvent.emit(Result.failure(Exception("서버 오류: ${e.message}")))
        } catch (e: Exception) {
            Log.e("SignUpViewModel", "알 수 없는 오류: ${e.message}")
            _signUpEvent.emit(Result.failure(Exception("알 수 없는 오류: ${e.message}")))
        }
    }

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 8
    }

    private fun isValidEmail(email: String): Boolean {
        return email.length in MIN_LENGTH..MAX_LENGTH
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length in MIN_LENGTH..MAX_LENGTH
    }

    private fun isValidHobby(hobby: String): Boolean {
        return hobby.length in MIN_LENGTH..MAX_LENGTH
    }
}

