package com.jaemin.features.data.dto.response

import com.jaemin.features.domain.entity.User

data class UserResponse(
    val explain : String?,
    val profileImage : String?,
    val username : String
)

fun UserResponse.toEntity() =
    User(this.explain, this.profileImage, this.username)