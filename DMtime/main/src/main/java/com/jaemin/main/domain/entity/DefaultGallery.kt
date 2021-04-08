package com.jaemin.main.domain.entity


data class DefaultGallery(val id: Int,
                          val name : String,
                          val posts: List<Post>)
