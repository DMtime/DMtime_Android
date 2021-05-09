package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PostLikeUseCase(private val postRepository: PostRepository) : UseCase<Pair<Int, String>, Unit>(){
    override fun build(data: Pair<Int, String>): Single<Unit> {
        return when (data.second) {
            Post.REACTION_DISLIKE -> {
                Observable.concat(postRepository.postDislikeCancel(data.first).toObservable<Unit>(), postRepository.postLike(data.first).toObservable())
                    .single(Unit)
            }
            Post.REACTION_LIKE -> {
                postRepository.postLikeCancel(data.first).toSingleDefault(Unit)
            }
            else -> {
                postRepository.postLike(data.first).toSingleDefault(Unit)
            }
        }
    }
}