package org.sopt.and.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.Regex.EMAIL_REGEX
import org.sopt.and.Regex.PASSWORD_REGEX
import org.sopt.and.domain.User

class SignUpViewModel : ViewModel() {
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _buttonClickable = MutableStateFlow(false)
    val buttonClickable: StateFlow<Boolean> = _buttonClickable

    private val emailPattern = EMAIL_REGEX.toRegex()

    fun onEmailChanged(email: String) {
        _user.value.email = email
        validateButtonState(email, _user.value.password)
    }

    fun onPasswordChanged(password: String) {
        _user.value.password = password
        validateButtonState(_user.value.email, password)
    }

    private fun validateButtonState(email: String, password: String) {
        _buttonClickable.value = isValidEmail(email) && isValidPassword(password)
    }

    fun signUp(email: String, password: String) {
        _user.value = User(email, password)
    }

    private fun isValidEmail(email: String): Boolean {
        return emailPattern.matches(email)
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length !in 8..20) {
            return false
        }

        val lowercase = password.count { it.isLowerCase() }
        val uppercase = password.count { it.isUpperCase() }
        val digit = password.count { it.isDigit() }
        val specialChar = password.count { it in PASSWORD_REGEX }

        val characterTypesCount =
            listOf(lowercase > 0, uppercase > 0, digit > 0, specialChar > 0).count { it }

        return characterTypesCount >= 3
    }
}
