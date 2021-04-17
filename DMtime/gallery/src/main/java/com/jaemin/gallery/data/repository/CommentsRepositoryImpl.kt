package com.jaemin.gallery.data.repository

import android.util.Log
import com.jaemin.gallery.data.dto.response.toEntity
import com.jaemin.gallery.data.remote.CommentsApi
import com.jaemin.gallery.domain.entity.Comments
import com.jaemin.gallery.domain.repository.CommentsRepository
import io.reactivex.rxjava3.core.Single

class CommentsRepositoryImpl(private val commentsApi: CommentsApi) : CommentsRepository {
    override fun getComments(postId : Int): Single<Comments> =
        commentsApi.getComments(postId).map {
            Log.d("ppap",it.toString())
            it.toEntity() }
}