package com.jaemin.features.data.remote

import com.jaemin.features.data.dto.response.GalleryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GalleryApi {
    @GET("board/galleries")
    fun getGalleries(@Query("gallery-type") galleryType: Int): Single<List<GalleryResponse>>

    @GET("board/galleries/{gallery-id}")
    fun getGallery(@Path("gallery-id") galleryId: String): Single<GalleryResponse>
}