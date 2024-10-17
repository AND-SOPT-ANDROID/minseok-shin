package org.sopt.and.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.and.domain.User

class SignUpViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun signUp(email: String, password: String) {
        _user.value = User(email, password)

    }
}

