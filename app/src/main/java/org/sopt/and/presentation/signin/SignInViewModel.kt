package org.sopt.and.presentation.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.model.request.RequestLoginDto
import org.sopt.and.data.model.response.ResponseFailedDto
import org.sopt.and.data.service.RetrofitInstance.userService
import org.sopt.and.domain.User
import retrofit2.HttpException
import java.io.IOException

class SignInViewModel(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _isLoginSuccessful = MutableLiveData<Boolean?>()
    val isLoginSuccessful: LiveData<Boolean?> = _isLoginSuccessful

    private val _uiEvent = MutableSharedFlow<SignInEvent>()
    val uiEvent: SharedFlow<SignInEvent> = _uiEvent


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
                _uiEvent.emit(SignInEvent.ShowSnackBar("로그인 성공"))
                _uiEvent.emit(SignInEvent.NavigateToMyPage(_user.value.email))
            } else {
                val errorBody = response.errorBody()?.string()
                val errorCode = if (errorBody != null) {
                    val errorData = Json.decodeFromString<ResponseFailedDto>(errorBody)
                    errorData.code
                } else {
                    "Unknown error code"
                }
                _uiEvent.emit(SignInEvent.ShowSnackBar("로그인 실패: $errorCode"))
                Log.e("ㅋㅋ", "Status code: ${response.code()} and error code: $errorCode")
                _isLoginSuccessful.value = false
            }
        } catch (e: IOException) {
            Log.e("ㅋㅋ", "네트워크 오류 발생: ${e.message}")
            _uiEvent.emit(SignInEvent.ShowSnackBar("네트워크 오류 발생: ${e.message}"))
            _isLoginSuccessful.value = false
        } catch (e: HttpException) {
            Log.e("ㅋㅋ", "HTTP 요청 실패: ${e.message}")
            _uiEvent.emit(SignInEvent.ShowSnackBar("서버 오류 발생: ${e.message}"))
            _isLoginSuccessful.value = false
        } catch (e: Exception) {
            Log.e("ㅋㅋ", "알 수 없는 오류 발생: ${e.message}")
            _uiEvent.emit(SignInEvent.ShowSnackBar("알 수 없는 오류가 발생했습니다: ${e.message}"))
            _isLoginSuccessful.value = false
        }
    }

    sealed class SignInEvent {
        data class ShowSnackBar(val message: String) : SignInEvent()
        data class NavigateToMyPage(val email: String) : SignInEvent()
    }
}
