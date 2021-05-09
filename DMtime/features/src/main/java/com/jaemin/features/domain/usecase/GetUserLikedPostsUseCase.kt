package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.repository.PostsRepository
import io.reactivex.rxjava3.core.Single

class GetUserLikedPostsUseCase (postsRepository: PostsRepository) : UserPostsUseCase(postsRepository) {
    override fun build(data: Pair<Int, String>): Single<Posts> =
        postsRepository.getUserLikedPosts(data.first, data.second)
}