package com.jaemin.posts.data.dto.response

import com.jaemin.posts.domain.entity.Uploader

data class UploaderResponse(val username: String)

fun UploaderResponse.toEntity() =
    Uploader(this.username)