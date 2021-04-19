package com.jaemin.features.data.dto.response

import com.jaemin.features.domain.entity.Posts


data class PostsResponse(
    val numberOfPages: Int,
    val posts: List<PostPreviewResponse>
)

fun PostsResponse.toEntity() =
    Posts(this.numberOfPages, this.posts.map { it.toEntity() })