package com.jaemin.main.data.dto.response

import com.jaemin.gallery.data.dto.response.PostPreviewResponse
import com.jaemin.gallery.data.dto.response.toEntity
import com.jaemin.main.domain.entity.DefaultGallery

data class DefaultGalleryResponse(val id: Int,
                                  val name : String,
                                  val posts: List<PostPreviewResponse>)

fun DefaultGalleryResponse.toEntity() = DefaultGallery(this.id,this.name,this.posts.map { it.toEntity() })
