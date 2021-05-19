package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Single

class DeletePostUseCase (private val postRepository: PostRepository) : UseCase<Int, Unit>() {
    override fun build(data: Int): Single<Unit> =
        postRepository.deletePost(data).toSingleDefault(Unit)
}