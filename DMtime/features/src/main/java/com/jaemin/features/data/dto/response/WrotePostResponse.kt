package com.jaemin.features.data.dto.response

import com.jaemin.features.domain.entity.WrotePost

data class WrotePostResponse(
    val id: Int
)
fun WrotePostResponse.toEntity() =
    WrotePost(this.id)