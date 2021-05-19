package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.Comments
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface CommentsRepository {
    fun getComments(postId : Int) : Single<Comments>

    fun writeComment(comment : Pair<Int, CommentInProgress>) : Single<Boolean>

    fun deleteComment(commentId : Int) : Completable
}