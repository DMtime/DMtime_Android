package com.jaemin.features.domain.entity


data class DefaultGallery(
    val id: String,
    val name: String,
    val posts: List<PostPreview>
)
