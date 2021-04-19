package com.jaemin.features.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.Uploader

data class UploaderResponse(val username: String,
                            @SerializedName("profile_image")
                            val profileImage: String)

fun UploaderResponse.toEntity() =
    Uploader(this.username,this.profileImage)