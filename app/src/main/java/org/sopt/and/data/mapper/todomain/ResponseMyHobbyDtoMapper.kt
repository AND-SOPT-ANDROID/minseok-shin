package org.sopt.and.data.mapper.todomain

import org.sopt.and.data.dataremote.model.response.ResponseMyHobbyDto
import org.sopt.and.domain.model.Hobby

fun ResponseMyHobbyDto.toDomain(): Hobby = Hobby(
    hobby = this.result.hobby
)
