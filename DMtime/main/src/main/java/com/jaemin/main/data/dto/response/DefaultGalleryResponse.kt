package com.jaemin.main.data.dto.response

import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.posts.data.dto.response.PostPreviewResponse
import com.jaemin.posts.data.dto.response.toEntity

data class DefaultGalleryResponse(val id: Int,
                                  val name : String,
                                  val posts: List<PostPreviewResponse>)

fun DefaultGalleryResponse.toEntity() = DefaultGallery(this.id,this.name,this.posts.map { it.toEntity() })
