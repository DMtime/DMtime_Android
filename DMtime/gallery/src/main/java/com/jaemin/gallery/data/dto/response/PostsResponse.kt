package com.jaemin.gallery.data.dto.response

import com.jaemin.gallery.domain.entity.Posts


data class PostsResponse(
    val numberOfPages: Int,
    val posts: List<PostPreviewResponse>
)

fun PostsResponse.toEntity() =
    Posts(this.numberOfPages, this.posts.map { it.toEntity() })