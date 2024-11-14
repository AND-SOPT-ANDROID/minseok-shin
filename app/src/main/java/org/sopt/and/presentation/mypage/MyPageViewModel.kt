package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.data.datalocal.datasource.UserInfoLocalDataSource
import org.sopt.and.domain.User

class MyPageViewModel(
    private val userInfoLocalDataSource: UserInfoLocalDataSource
) : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    init {
        _user.value = User(
            email = userInfoLocalDataSource.nickname,
            hobby = userInfoLocalDataSource.accessToken
        )
    }

    fun getUerEmail(): String {
        return _user.value.email
    }

    fun getUerHobby(): String {
        return _user.value.hobby
    }

    fun updateUserEmail(email: String) {
        _user.value.email = email
    }

}
