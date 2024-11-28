package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFailedDto(
    @SerialName("code")
    val code: String
)

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

