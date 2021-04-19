package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Single

class GetPostUseCase(private val postRepository: PostRepository) : UseCase<Int, Post>() {
    override fun build(data: Int): Single<Post> =
        postRepository.getPost(data)
}