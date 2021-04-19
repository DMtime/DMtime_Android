package com.jaemin.features.domain.entity

data class Comment(
    val content : String,
    val isAnonymous : Boolean,
    val id : Int,
    val upperCommentId : Int?,
    val writer : Uploader,
    val wroteDatetime : String,
    val wrotePost : WrotePost
)
