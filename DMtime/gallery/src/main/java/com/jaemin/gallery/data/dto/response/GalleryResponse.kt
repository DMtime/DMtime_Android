package com.jaemin.gallery.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.Gallery

data class GalleryResponse(
    val name: String,
    val explain: String,
    @SerializedName("gallery_id")
    val galleryId: String,
    @SerializedName("gallery_type")
    val galleryType: Int
)

fun GalleryResponse.toEntity() =
    Gallery(this.name, this.explain, this.galleryId, this.galleryType)