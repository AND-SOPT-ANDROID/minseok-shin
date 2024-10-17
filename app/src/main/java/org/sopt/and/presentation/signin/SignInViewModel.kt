package org.sopt.and.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.domain.User

class SignInViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun updateUser(email: String, password: String) {
        _user.value = User(email, password)
    }

    fun signIn(email: String, password: String) {
        _user.value = User(email, password)
    }
}
