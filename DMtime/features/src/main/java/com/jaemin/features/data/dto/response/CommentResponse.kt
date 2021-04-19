package com.jaemin.features.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.Comment

data class CommentResponse(
    val content : String,
    @SerializedName("is_anonymous")
    val isAnonymous : Boolean,
    val id : Int,
    @SerializedName("upper_comment_id")
    val upperCommentId : Int?,
    val writer : UploaderResponse,
    @SerializedName("wrote_datetime")
    val wroteDatetime : String,
    @SerializedName("wrote_post")
    val wrotePost : WrotePostResponse
)
fun CommentResponse.toEntity() =
    Comment(this.content,this.isAnonymous,this.id,this.upperCommentId,this.writer.toEntity(),this.wroteDatetime,this.wrotePost.toEntity())