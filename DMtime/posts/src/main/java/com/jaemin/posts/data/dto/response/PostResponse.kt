package com.jaemin.posts.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.Gallery
import com.jaemin.posts.domain.entity.Post

data class PostResponse(
    val content: String,
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    val id: Int,
    val images: List<String>,
    @SerializedName("number_of_likes")
    val numberOfLikes: Int,
    @SerializedName("number_of_dislikes")
    val numberOfDislikes: Int,
    @SerializedName("posted_datetime")
    val postedDatetime: String,
    @SerializedName("posted_gallery")
    val postedGallery: Gallery,
    val title: String,
    val uploader: Uploader,
    val views: Int
)

fun PostResponse.toEntity() = Post(
    this.content,
    this.isAnonymous,
    this.id,
    this.images,
    this.numberOfLikes,
    this.numberOfDislikes,
    this.postedDatetime,
    this.postedGallery,
    this.title,
    this.uploader,
    this.views
)
