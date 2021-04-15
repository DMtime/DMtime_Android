package com.jaemin.gallery.presentation.presenter

import android.util.Log
import com.jaemin.gallery.domain.entity.Post
import com.jaemin.gallery.domain.usecase.GetPostUseCase
import com.jaemin.gallery.presentation.contract.PostContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class PostPresenter(
    private val getPostUseCase: GetPostUseCase,
    private val postView: PostContract.View
) : PostContract.Presenter {
    override fun onCreate() {
        getPostUseCase.execute(postView.getPostId(), object : DisposableSingleObserver<Post>() {
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