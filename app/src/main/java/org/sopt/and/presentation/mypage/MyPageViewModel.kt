package org.sopt.and.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.domain.User

class MyPageViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun loadUser(user: User) {
        _user.value = user
    }

    fun clearUser() {
        _user.value = null
    }
}
