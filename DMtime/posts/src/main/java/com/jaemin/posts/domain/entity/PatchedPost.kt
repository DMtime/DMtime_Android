package com.jaemin.posts.domain.entity


data class PatchedPost(
    val imageIds: List<Int>,
    val content: String,
    val title: String
)
