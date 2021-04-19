package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.repository.CommentsRepository
import io.reactivex.rxjava3.core.Single

class GetCommentsUseCase(private val commentsRepository: CommentsRepository) : UseCase<Int, Comments>(){
    override fun build(data: Int): Single<Comments> =
        commentsRepository.getComments(data)
}