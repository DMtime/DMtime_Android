package com.jaemin.gallery.data.dto.response

import com.google.gson.annotations.SerializedName

data class GalleryResponse(
    val name: String,
    val explain: String,
    @SerializedName("gallery_type")
    val galleryType: String,
    @SerializedName("gallery_id")
    val galleryId: Int,
)