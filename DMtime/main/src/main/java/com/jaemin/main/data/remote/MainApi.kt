package com.jaemin.main.data.remote

import com.jaemin.gallery.data.dto.response.PostsResponse
import com.jaemin.main.data.dto.response.GalleryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("board/galleries")
    fun getDefaultGalleries(@Query("gallery-type") galleryType: Int) : Single<List<GalleryResponse>>

    @GET("board/posts")
    fun getGalleryPosts(@Query("per-page") perPage : Int,@Query("page") page : Int,@Query("gallery-id") galleryId : Int) : Single<PostsResponse>

//    @GET("users/{username}")
//    fun getUserInfo(@Path("username") username : String) : Single<PostsResponse>

}