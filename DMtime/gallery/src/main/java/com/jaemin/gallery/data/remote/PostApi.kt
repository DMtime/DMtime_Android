package com.jaemin.gallery.data.remote


import com.jaemin.gallery.data.dto.request.PatchedPostRequest
import com.jaemin.gallery.data.dto.request.WrittenPostRequest
import com.jaemin.gallery.data.dto.response.PostResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface PostApi {

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