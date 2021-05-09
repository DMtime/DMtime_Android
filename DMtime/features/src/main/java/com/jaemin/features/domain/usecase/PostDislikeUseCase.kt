package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PostDislikeUseCase(private val postRepository: PostRepository) : UseCase<Pair<Int, String>, Unit>(){
    override fun build(data: Pair<Int, String>): Single<Unit> {
        return when (data.second) {
            Post.REACTION_LIKE -> {
                Observable.concat(postRepository.postLikeCancel(data.first).toObservable<Unit>(),postRepository.postDislike(data.first).toObservable())
                    .single(Unit)
            }
            Post.REACTION_DISLIKE -> {
                postRepository.postDislikeCancel(data.first).toSingleDefault(Unit)
            }
            else -> {
                postRepository.postDislike(data.first).toSingleDefault(Unit)
            }
        }
    }
}