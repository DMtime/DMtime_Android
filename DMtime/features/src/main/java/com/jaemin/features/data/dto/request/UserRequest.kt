package com.jaemin.features.data.dto.request

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.User

data class UserRequest(
    @SerializedName("user_explain")
    val userExplain : String?,
    @SerializedName("profile_image")
    val profileImage : String?,
    val username : String
)

fun User.toData() =
    UserRequest(this.userExplain, this.profileImage, this.username)
