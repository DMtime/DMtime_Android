package com.jaemin.features.data.repository

import android.util.Log
import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.CommentsApi
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.repository.CommentsRepository
import io.reactivex.rxjava3.core.Single

class CommentsRepositoryImpl(private val commentsApi: CommentsApi) : CommentsRepository {
    override fun getComments(postId : Int): Single<Comments> =
        commentsApi.getComments(postId).map {
            Log.d("ppap",it.toString())
            it.toEntity() }
}