package com.jaemin.gallery.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.Comments


data class CommentsResponse(
    val comments : List<CommentResponse>,
    @SerializedName("number_of_pages")
    val numberOfPages : Int
)

fun CommentsResponse.toEntity() =
    Comments(this.comments.map { it.toEntity()},this.numberOfPages)