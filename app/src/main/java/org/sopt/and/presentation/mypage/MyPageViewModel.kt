package org.sopt.and.presentation.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.data.service.RetrofitInstance.userService
import org.sopt.and.domain.User

class MyPageViewModel(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            val nickname = userInfoLocalDataSource.nickname
            val hobby = loadUserHobby()
            _user.value = User(
                email = nickname,
                hobby = hobby
            )
        }
    }

    private suspend fun loadUserHobby(): String {
        return try {
            val response = userService.getUserHobby(userInfoLocalDataSource.accessToken)
            if (response.isSuccessful) {
                Log.d("ㅋㅋ", "Status code: ${response.code()},Error code: ${response.body()?.code}")
                response.body()?.result?.hobby ?: "에러났지렁이"
            } else {
                Log.e("ㅋㅋ", "Status code: ${response.code()}, Error code: ${response.body()?.code}")
                "에러났지렁이"
            }
        } catch (e: Exception) {
            Log.e("MyPageViewModel", "Exception while fetching hobby: ${e.localizedMessage}", e)
            "에러났지렁이"
        }
    }
}
