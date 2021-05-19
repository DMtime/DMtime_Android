package com.jaemin.features.domain.requestmodel


data class AddGallery( val galleryType : Int,
                       val galleryId : String,
                       val explain : String,
                       val name : String)