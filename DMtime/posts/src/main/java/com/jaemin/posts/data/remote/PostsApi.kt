package com.jaemin.posts.data.remote


import com.jaemin.posts.data.dto.request.PatchedPostRequest
import com.jaemin.posts.data.dto.request.WrittenPostRequest
import com.jaemin.posts.data.dto.response.PostResponse
import com.jaemin.posts.data.dto.response.PostsResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface PostsApi {

    @GET("board/posts")
    fun getGalleryPosts(
        @Query("per-page") perPage: Int,
        @Query("page") page: Int,
        @Query("gallery-id") galleryId: Int
    ): Single<PostsResponse>

    @GET("board/posts/{post-id}")
    fun getPost(@Path("post-id") postId: Int): Single<PostResponse>

    @PATCH("board/posts/{post-id}")
    fun patchPost(
        @Path("post-id") postId: Int,
        @Body PatchedPostRequest: PatchedPostRequest
    ): Completable

    @GET("board/posts")
    fun writePost(@Body writtenPostRequest: WrittenPostRequest): Completable

}