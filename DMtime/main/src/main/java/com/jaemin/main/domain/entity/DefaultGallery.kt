package com.jaemin.main.domain.entity

import com.jaemin.gallery.domain.entity.PostPreview


data class DefaultGallery(
    val id: Int,
    val name: String,
    val posts: List<PostPreview>
)
