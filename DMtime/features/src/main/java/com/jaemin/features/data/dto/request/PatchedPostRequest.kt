package com.jaemin.features.data.dto.request

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.PatchedPost

data class PatchedPostRequest(
    @SerializedName("image_ids")
    val imageIds: List<Int>,
    val content: String,
    val title: String
)

fun PatchedPost.toData() =
    PatchedPostRequest(this.imageIds, this.content, this.title)
