package com.jaemin.features.presentation.post.presenter

import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.usecase.GetPostUseCase
import com.jaemin.features.presentation.post.contract.PostContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class PostPresenter(
    private val getPostUseCase: GetPostUseCase,
    private val postView: PostContract.View
) : PostContract.Presenter {
    override fun onCreate(postId : Int) {
        getPostUseCase.execute(postId, object : DisposableSingleObserver<Post>() {
            override fun onSuccess(post: Post) {
                postView.setPost(post)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                postView.showErrorScreen()
            }
        })

    }

    override fun onClickLikeButton() {

    }

    override fun onClickDislikeButton() {
    }

}