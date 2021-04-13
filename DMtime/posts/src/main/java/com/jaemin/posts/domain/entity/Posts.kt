package com.jaemin.posts.domain.entity

data class Posts(
    val numberOfPages: Int,
    val posts: List<PostPreview>
)
