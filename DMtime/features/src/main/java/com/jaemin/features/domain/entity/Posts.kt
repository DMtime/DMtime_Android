package com.jaemin.features.domain.entity

data class Posts(
    val numberOfPages: Int,
    val posts: List<PostPreview>
)
