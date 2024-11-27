package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.dataremote.model.response.ResponseLoginDto
import org.sopt.and.domain.model.Token

fun ResponseLoginDto.toDomain(): Token = Token(
    token = this.result.token
)
