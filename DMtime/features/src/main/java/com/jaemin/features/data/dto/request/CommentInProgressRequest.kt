
package com.jaemin.features.data.dto.request

import com.google.gson.annotations.SerializedName

data class CommentInProgressRequest(
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    @SerializedName("upper_comment_id")
    val upperCommentId: Int,
    val content: String
)
