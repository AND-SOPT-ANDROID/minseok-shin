package org.sopt.and.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseSignUpDto(
    @SerialName("result")
    val result: Result
) {
    @Serializable
    data class Result(
        @SerialName("no")
        val no: Int
    )
}

@Serializable
data class ResponseSignUpFailedDto(
    @SerialName("code")
    val code: String
)
