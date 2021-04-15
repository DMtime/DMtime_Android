package com.jaemin.gallery.data.remote


import com.jaemin.gallery.data.dto.response.PostsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PostsApi {

    @GET("board/posts")
    fun getGalleryPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int,
        @Query("gallery-id") galleryId: String
    ): Single<PostsResponse>

}