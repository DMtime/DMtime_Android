package com.jaemin.gallery.domain.entity

data class WrittenPost(
    val isAnonymous: Boolean,
    val imageIds: List<Int>,
    val content: String,
    val title: String
)
