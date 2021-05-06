package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Single

class WritePostsUseCase(private val postRepository: PostRepository) : UseCase<Pair<String, WrittenPost>, Boolean>() {
    override fun build(data: Pair<String, WrittenPost>): Single<Boolean> {
        return postRepository.writePost(data)
    }
}