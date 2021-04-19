package com.jaemin.features.data.dto.response

import com.jaemin.features.domain.entity.DefaultGallery


data class DefaultGalleryResponse(
    val id: String,
    val name: String,
    val posts: List<PostPreviewResponse>
)

fun DefaultGalleryResponse.toEntity() = DefaultGallery(this.id,this.name,this.posts.map { it.toEntity() })
