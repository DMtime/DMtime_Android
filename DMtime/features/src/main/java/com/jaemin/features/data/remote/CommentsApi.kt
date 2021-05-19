package com.jaemin.features.data.remote

import com.jaemin.features.data.dto.request.CommentInProgressRequest
import com.jaemin.features.data.dto.response.CommentsResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface CommentsApi {
    @GET("board/comments")
    fun getComments(@Query("post-id") postId: Int): Single<CommentsResponse>

    @POST("board/comments")
    fun postComment(
        @Query("post-id") postId: Int,
        @Body commentInProgressRequest: CommentInProgressRequest
    ): Completable

    @DELETE("board/comments/{comment-id}")
    fun deleteComment(@Path("comment-id") commentId: Int): Completable
}