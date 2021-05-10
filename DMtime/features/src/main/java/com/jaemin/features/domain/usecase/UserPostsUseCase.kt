package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.repository.PostsRepository

abstract class UserPostsUseCase(val postsRepository: PostsRepository)  :UseCase<Pair<Int, String>, Posts>()