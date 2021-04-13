package com.jaemin.posts.domain.usecase

import com.jaemin.posts.domain.repository.PostsRepository

class GetPostsUseCase(private val postsRepository: PostsRepository)