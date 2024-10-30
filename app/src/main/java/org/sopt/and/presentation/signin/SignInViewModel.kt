package org.sopt.and.presentation.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.domain.User

class SignInViewModel : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _isLoginSuccessful = MutableStateFlow<Boolean?>(null)
    val isLoginSuccessful = _isLoginSuccessful.asStateFlow()

    fun updateUser(email: String, password: String) {
        _user.value = User(email, password)
    }

    fun signIn(email: String, password: String): Boolean {
        if (email.isNotEmpty() && password.isNotEmpty() &&
            email == _user.value.email && password == _user.value.password
        ) {
            _user.value = User(email, password)
            return true
        } else return false
    }
}
