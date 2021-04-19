package com.jaemin.features.domain.entity


data class Post(
    val content: String,
    val isAnonymous: Boolean,
    val id: Int,
    val images: List<String>,
    val numberOfLikes: Int,
    val numberOfDislikes: Int,
    val postedDatetime: String,
    val postedGallery: Gallery,
    val title: String,
    val uploader: Uploader,
    val views: Int,
)
