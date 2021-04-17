package com.jaemin.gallery.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.Gallery
import com.jaemin.gallery.domain.entity.PostPreview
import com.jaemin.gallery.domain.entity.Uploader


data class PostPreviewResponse(
    @SerializedName("number_of_comments")
    val numberOfComments: Int,
    @SerializedName("my_reaction")
    val myReaction: String,
    val id: Int,
    val uploader: Uploader,
    @SerializedName("posted_datetime")
    val postedDatetime: String,
    @SerializedName("number_of_likes")
    val numberOfLikes: Int,
    val title: String,
    @SerializedName("whether_exist_image")
    val whetherExistImage: Boolean,
    @SerializedName("is_anonymous")
    val isAnonymous: Boolean,
    val views: Int,
    @SerializedName("posted_gallery")
    val postedGallery: Gallery,
    @SerializedName("number_of_dislikes")
    val numberOfDisLikes: Int
)

fun PostPreviewResponse.toEntity() = PostPreview(
    this.numberOfComments,
    this.myReaction,
    this.id,
    this.uploader,
    this.postedDatetime,
    this.numberOfLikes,
    this.title,
    this.whetherExistImage
    ,this.isAnonymous,
    this.views,
    this.postedGallery,
    this.numberOfDisLikes
)

