package com.jaemin.gallery.domain.entity


data class PostPreview(
    val numberOfComments: Int,
    val myReaction: String,
    val id: Int,
    val uploader: Uploader,
    val postedDatetime: String,
    val numberOfLikes: Int,
    val title: String,
    val whetherExistImage: Boolean,
    val isAnonymous: Boolean,
    val views: Int,
    val postedGallery: Gallery,
    val numberOfDisLikes: Int
    )
