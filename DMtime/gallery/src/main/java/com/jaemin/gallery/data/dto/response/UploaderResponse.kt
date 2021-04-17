package com.jaemin.gallery.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.Uploader

data class UploaderResponse(val username: String,
                            @SerializedName("profile_image")
                            val profileImage: String)

fun UploaderResponse.toEntity() =
    Uploader(this.username,this.profileImage)