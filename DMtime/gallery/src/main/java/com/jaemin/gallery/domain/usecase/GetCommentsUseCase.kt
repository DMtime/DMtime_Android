package com.jaemin.gallery.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.gallery.domain.entity.Comments
import com.jaemin.gallery.domain.repository.CommentsRepository
import io.reactivex.rxjava3.core.Single

class GetCommentsUseCase(private val commentsRepository: CommentsRepository) : UseCase<Int, Comments>(){
    override fun build(data: Int): Single<Comments> =
        commentsRepository.getComments(data)
}