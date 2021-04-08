package com.jaemin.main.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.main.domain.entity.DefaultGallery

data class DefaultGalleryResponse(val id: Int,
                                  val name : String,
                                  val posts: List<PostResponse>)

fun DefaultGalleryResponse.toEntity() = DefaultGallery(this.id,this.name,this.posts.map { it.toEntity() })
