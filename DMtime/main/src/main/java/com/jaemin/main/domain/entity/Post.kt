package com.jaemin.main.domain.entity


data class Post(val id: Int,
                val likes: Int,
                val numberOfComments: Int,
                val postedDatetime: String,
                val title: String,
                val uploader: Uploader,
                val views: Int,
                val whetherExistImage: Boolean)
