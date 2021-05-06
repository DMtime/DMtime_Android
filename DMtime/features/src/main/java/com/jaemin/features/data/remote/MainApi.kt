package com.jaemin.features.data.remote

import com.jaemin.features.data.dto.response.GalleryResponse
import com.jaemin.features.data.dto.response.PostsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("board/galleries")
    fun getDefaultGalleries(@Query("gallery-type") galleryType: Int) : Single<List<GalleryResponse>>

    @GET("board/galleries")
    fun getAllGalleries() : Single<List<GalleryResponse>>

    @GET("board/posts")
    fun getGalleryPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int,
        @Query("gallery-id") galleryId: String
    ): Single<PostsResponse>
}