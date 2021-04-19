package com.jaemin.features.data.dto.response

import com.jaemin.features.domain.entity.PostedGallery

data class PostedGalleryResponse(
    val id: String,
    val name: String
)

fun PostedGalleryResponse.toEntity() =
    PostedGallery(id, name)