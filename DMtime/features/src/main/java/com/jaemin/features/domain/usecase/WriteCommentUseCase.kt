package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Comment
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.domain.repository.CommentsRepository
import com.jaemin.features.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Single

class WriteCommentUseCase(private val commentsRepository: CommentsRepository) : UseCase<Pair<Int,CommentInProgress>, Boolean>() {
    override fun build(data: Pair<Int, CommentInProgress>): Single<Boolean> {
        return commentsRepository.writeComment(data)
    }
}