package com.jaemin.features.domain.entity

import java.io.File

data class WrittenPost(
    val isAnonymous: Boolean,
    val images: List<File>,
    val content: String,
    val title: String
)
