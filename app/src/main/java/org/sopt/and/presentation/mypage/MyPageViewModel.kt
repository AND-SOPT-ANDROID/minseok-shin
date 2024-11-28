package org.sopt.and.presentation.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.datalocal.datasource.UserLocalDataSource
import org.sopt.and.domain.model.User
import org.sopt.and.domain.usecase.GetMyHobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userInfoLocalDataSource: UserLocalDataSource,
    private val getMyHobbyUseCase: GetMyHobbyUseCase
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
            val response = getMyHobbyUseCase(userInfoLocalDataSource.accessToken)
            if (response.isSuccessful) {
                Log.d("ㅋㅋ", "Status code: ${response.code()}")
                response.body()?.result?.hobby ?: "내 취미 내놔!!"
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
