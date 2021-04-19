package com.jaemin.features.domain.entity

data class CommentInProgress(
    val isAnonymous: Boolean,
    val upperCommentId: Int,
    val content: String
)
