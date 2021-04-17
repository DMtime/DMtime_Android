package com.jaemin.gallery.domain.entity

data class CommentInProgress(
    val isAnonymous: Boolean,
    val upperCommentId: Int,
    val content: String
)
