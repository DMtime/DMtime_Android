package com.jaemin.gallery.data.dto.response

import com.jaemin.gallery.domain.entity.Uploader

data class UploaderResponse(val username: String)

fun UploaderResponse.toEntity() =
    Uploader(this.username)