package com.jaemin.features.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.Uploader

data class PostResponse(
    val content: String,
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    @SerializedName("is_mine")
    val isMine: Boolean,
    val id: Int,
    val images: List<String>,
    @SerializedName("number_of_likes")
    val numberOfLikes: Int,
    @SerializedName("number_of_dislikes")
    val numberOfDislikes: Int,
    @SerializedName("posted_datetime")
    val postedDatetime: String,
    @SerializedName("posted_gallery")
    val postedGallery: GalleryResponse,
    val title: String,
    val uploader: Uploader,
    val views: Int,
    @SerializedName("my_reaction")
    val myReaction: String

)

fun PostResponse.toEntity() = Post(
    this.content,
    this.isAnonymous,
    this.isMine,
    this.id,
    this.images,
    this.numberOfLikes,
    this.numberOfDislikes,
    this.postedDatetime,
    this.postedGallery.toEntity(),
    this.title,
    this.uploader,
    this.views,
    this.myReaction
)
