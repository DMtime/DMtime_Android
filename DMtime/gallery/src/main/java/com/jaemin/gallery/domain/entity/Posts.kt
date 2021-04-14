package com.jaemin.gallery.domain.entity

data class Posts(
    val numberOfPages: Int,
    val posts: List<PostPreview>
)
