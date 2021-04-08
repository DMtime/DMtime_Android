package com.jaemin.main.data.dto.response

import com.google.gson.annotations.SerializedName

data class GalleryResponse(val id: Int,
                           val name : String,
                           @SerializedName("gallery_type")
                           val galleryType : String,
                           val explain : String
                           )