package org.sopt.and

import kotlinx.serialization.Serializable

object Regex {
    const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
    const val PASSWORD_REGEX = "!@#\$%^&*() _+\\-=\\[\\]{};':\"\\\\|,.<>\\/?"
}

object ImageRatio {
    const val LARGE = 0.43f
    const val SMALL = 0.29f
}

@Serializable
sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data class SignIn(
        val email: String,
        val password: String
    ) : Route()

    @Serializable
    data object SignUp : Route()

    @Serializable
    data class MyPage(
        val email: String
    ) : Route()

    @Serializable
    data object Search : Route()
}
