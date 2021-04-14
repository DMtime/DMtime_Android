package com.jaemin.posts.domain.usecase

import com.jaemin.base.UseCase
import io.reactivex.rxjava3.core.Single

class GetPostsUseCase(private val postsRepository: PostsRepository) : UseCase<Int, Posts>(){
    override fun build(data: Int,page: Int): Single<Posts> {
        postsRepository.getPosts(4,1,)
    }

}