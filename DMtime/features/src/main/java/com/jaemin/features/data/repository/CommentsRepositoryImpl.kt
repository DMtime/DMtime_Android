package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.request.toData
import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.CommentsApi
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.repository.CommentsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class CommentsRepositoryImpl(private val commentsApi: CommentsApi) : CommentsRepository {
    override fun getComments(postId: Int): Single<Comments> =
        commentsApi.getComments(postId).map {
            it.toEntity()
        }

    override fun writeComment(comment: Pair<Int, CommentInProgress>): Single<Boolean> =
        commentsApi.postComment(comment.first, comment.second.toData()).toSingleDefault(true)
            .onErrorReturnItem(false)

    override fun deleteComment(commentId: Int): Completable =
        commentsApi.deleteComment(commentId)

}