package com.jaemin.features.data.dto.request

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.requestmodel.AddGallery

data class AddGalleryRequest(
    @SerializedName("gallery_type")
    val galleryType : Int,
    @SerializedName("gallery_id")
    val galleryId : String,
    val explain : String,
    val name : String
    )
fun AddGallery.toData() =
    AddGalleryRequest(this.galleryType, this.galleryId, this.explain, this.name)