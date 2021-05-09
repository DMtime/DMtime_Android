package com.jaemin.features.data.remote


import com.jaemin.features.data.dto.response.PostsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PostsApi {

    @GET("board/posts")
    fun getGalleryPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int,
        @Query("gallery-id") galleryId: String
    ): Single<PostsResponse>

    @GET("board/posts")
    fun getUserPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int,
        @Query("username") username: String
    ): Single<PostsResponse>

    @GET("users/{username}/posts-wrote-comment")
    fun getUserWroteCommentPosts(
        @Path("username") username : String,
        @Query("per-page") perPage: Int,
        @Query("page") page: Int
    ): Single<PostsResponse>

    @GET("users/{username}/liked-posts")
    fun getUserLikedPosts(
        @Path("username") username : String,
        @Query("per-page") perPage: Int,
        @Query("page") page: Int
    ): Single<PostsResponse>

    @GET("me/posts-wrote-comment")
    fun getMyWroteCommentPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int
    ): Single<PostsResponse>

    @GET("me/liked-posts")
    fun getMyLikedPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int
    ): Single<PostsResponse>

}