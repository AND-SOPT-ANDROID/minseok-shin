package org.sopt.and.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.data.dataremote.model.request.RequestSignUpDto
import org.sopt.and.data.dataremote.model.response.ResponseFailedDto
import org.sopt.and.domain.model.User
import org.sopt.and.domain.usecase.PostSignUpUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignUpUseCase: PostSignUpUseCase
) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _buttonClickable = MutableStateFlow(false)
    val buttonClickable: StateFlow<Boolean> = _buttonClickable

    private val _signUpResult = MutableSharedFlow<Result<Unit>>()
    val signUpResult: SharedFlow<Result<Unit>> = _signUpResult

    fun onEmailChanged(email: String) {
        _user.value = _user.value.copy(email = email)
        validateButtonState()
    }

    fun onPasswordChanged(password: String) {
        _user.value = _user.value.copy(password = password)
        validateButtonState()
    }

    fun onHobbyChanged(hobby: String) {
        _user.value = _user.value.copy(hobby = hobby)
        validateButtonState()
    }

    private fun validateButtonState() {
        _buttonClickable.value = isValidEmail(_user.value.email) &&
                isValidPassword(_user.value.password) &&
                isValidHobby(_user.value.hobby)
    }

    suspend fun signUp() {
        val requestDto = RequestSignUpDto(
            username = _user.value.email,
            password = _user.value.password,
            hobby = _user.value.hobby
        )

        try {
            val response = postSignUpUseCase(requestDto)
            if (response.isSuccessful) {
                _signUpResult.emit(Result.success(Unit))
                Log.d("SignUpViewModel", "회원가입 성공: Status code ${response.code()}")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorCode = if (errorBody != null) {
                    val errorData = Json.decodeFromString<ResponseFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }
                _signUpResult.emit(
                    Result.failure(
                        Exception("회원가입 실패: Status code ${response.code()}, Error code $errorCode")
                    )
                )
            }
        } catch (e: IOException) {
            Log.e("SignUpViewModel", "네트워크 오류: ${e.message}")
            _signUpResult.emit(Result.failure(Exception("네트워크 오류: ${e.message}")))
        } catch (e: HttpException) {
            Log.e("SignUpViewModel", "서버 오류: ${e.message}")
            _signUpResult.emit(Result.failure(Exception("서버 오류: ${e.message}")))
        } catch (e: Exception) {
            Log.e("SignUpViewModel", "알 수 없는 오류: ${e.message}")
            _signUpResult.emit(Result.failure(Exception("알 수 없는 오류: ${e.message}")))
        }
    }

    companion object {
        private const val MIN_LENGTH = 1
        private const val MAX_LENGTH = 8
    }

    private fun isValidEmail(email: String): Boolean = email.length in MIN_LENGTH..MAX_LENGTH
    private fun isValidPassword(password: String): Boolean =
        password.length in MIN_LENGTH..MAX_LENGTH

    private fun isValidHobby(hobby: String): Boolean = hobby.length in MIN_LENGTH..MAX_LENGTH
}

