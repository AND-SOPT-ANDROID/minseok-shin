package org.sopt.and.data.dataremote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMyHobbyDto(
    @SerialName("result")
    val result: Result,
    @SerialName("code")
    val code: String
)

@Serializable
data class Result(
    @SerialName("hobby")
    val hobby: String
)