package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.CommentsRepository
import io.reactivex.rxjava3.core.Single

class DeleteCommentUseCase(private val commentsRepository: CommentsRepository) : UseCase<Int, Unit>() {
    override fun build(data: Int): Single<Unit> =
        commentsRepository.deleteComment(data).toSingleDefault(Unit)
}