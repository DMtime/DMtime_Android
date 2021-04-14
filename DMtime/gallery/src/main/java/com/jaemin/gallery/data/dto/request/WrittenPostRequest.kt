package com.jaemin.gallery.data.dto.request

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.WrittenPost

data class WrittenPostRequest(
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    @SerializedName("image_ids")
    val imageIds: List<Int>,
    val content: String,
    val title: String
)

fun WrittenPost.toData() =
    WrittenPostRequest(this.isAnonymous, this.imageIds, this.content, this.title)
