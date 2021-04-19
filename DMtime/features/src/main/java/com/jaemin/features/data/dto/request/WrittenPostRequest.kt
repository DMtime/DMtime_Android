package com.jaemin.features.data.dto.request

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.Image
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.util.FileManager
import java.io.File

data class WrittenPostRequest(
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    @SerializedName("images")
    val images: List<String>,
    val content: String,
    val title: String
)

//fun WrittenPost.toData() =
//    WrittenPostRequest(this.isAnonymous, this.images, this.content, this.title)
