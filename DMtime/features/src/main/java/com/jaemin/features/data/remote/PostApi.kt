package com.jaemin.features.data.remote


import com.jaemin.features.data.dto.request.PatchedPostRequest
import com.jaemin.features.data.dto.request.WrittenPostRequest
import com.jaemin.features.data.dto.response.PostResponse
import com.jaemin.features.domain.entity.Image
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

    @POST("board/posts")
    fun writePost(
        @Query("gallery-id") galleryId: String,
        @Body writtenPostRequest: WrittenPostRequest
    ): Completable

    @Multipart
    @POST("images")
    fun postImage(@Part image: MultipartBody.Part): Single<Image>

    @POST("board/posts/{post-id}/like")
    fun postLike(@Path("post-id") postId: Int): Completable

    @POST("board/posts/{post-id}/dislike")
    fun postDislike(@Path("post-id") postId: Int): Completable

    @DELETE("board/posts/{post-id}/like")
    fun postLikeCancel(@Path("post-id") postId: Int): Completable

    @DELETE("board/posts/{post-id}/dislike")
    fun postDislikeCancel(@Path("post-id") postId: Int): Completable
}