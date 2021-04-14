package com.jaemin.gallery.data.dto.response

import com.jaemin.gallery.domain.entity.PostedGallery

data class PostedGalleryResponse(
    val id: String,
    val name: String
)

fun PostedGalleryResponse.toEntity() =
    PostedGallery(id, name)