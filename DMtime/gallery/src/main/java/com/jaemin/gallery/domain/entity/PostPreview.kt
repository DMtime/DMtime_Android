package com.jaemin.gallery.domain.entity


data class PostPreview(
    val id: Int,
    val likes: Int,
    val numberOfComments: Int,
    val postedDatetime: String,
    val title: String,
    val uploader: Uploader,
    val views: Int,
    val whetherExistImage: Boolean
)
