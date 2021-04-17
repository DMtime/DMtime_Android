package com.jaemin.gallery.data.dto.response

import com.jaemin.gallery.domain.entity.WrotePost

data class WrotePostResponse(
    val id: Int
)
fun WrotePostResponse.toEntity() =
    WrotePost(this.id)