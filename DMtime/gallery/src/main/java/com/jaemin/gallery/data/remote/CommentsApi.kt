package com.jaemin.gallery.data.remote

import com.jaemin.gallery.data.dto.request.CommentInProgressRequest
import com.jaemin.gallery.data.dto.response.CommentsResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface CommentsApi {
    @GET("board/comments")
    fun getComments(@Query("post-id") postId: Int) : Single<CommentsResponse>

    @POST("board/comments")
    fun postComment(@Query("post-id") postId: Int,@Body commentInProgressRequest: CommentInProgressRequest) : Completable
}