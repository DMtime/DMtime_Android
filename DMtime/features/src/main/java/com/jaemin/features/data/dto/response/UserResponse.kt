package com.jaemin.features.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.User

data class UserResponse(
    val explain : String?,
    @SerializedName("profile_image")
    val profileImage : String?,
    val username : String
)

fun UserResponse.toEntity() =
    User(this.explain, this.profileImage, this.username)