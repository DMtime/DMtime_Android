package com.jaemin.features.data.remote


import com.jaemin.features.data.dto.request.PatchedPostRequest
import com.jaemin.features.data.dto.request.WrittenPostRequest
import com.jaemin.features.data.dto.response.PostResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

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

    @Multipart
    @POST("images")
    fun postImage(@Part image: MultipartBody.Part): Single<String>
}