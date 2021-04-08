package com.jaemin.main.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.main.domain.entity.Uploader

data class UploaderResponse(val username : String)

fun UploaderResponse.toEntity()=
    Uploader(this.username)