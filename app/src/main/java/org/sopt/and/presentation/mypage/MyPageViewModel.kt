package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.domain.User

class MyPageViewModel : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    fun getUerEmail(): String {
        return _user.value.email
    }


    fun updateUserEmail(email: String) {
        _user.value.email = email
    }

}
