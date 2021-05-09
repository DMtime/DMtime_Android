package com.jaemin.features.presentation.mypage.presenter

import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.usecase.*
import com.jaemin.features.presentation.mypage.contract.PostsContract
import com.jaemin.features.presentation.mypage.contract.UserPostsContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

open class UserPostsPresenter<T : UserPostsUseCase>(
    private val postsView: UserPostsContract.View,
    private val getPostsUseCase: T
) : UserPostsContract.Presenter {

    private var page = 1
    private val username by lazy {
        postsView.getUsername()
    }
    override fun onCreate() {
        page = 1
        getPostsUseCase.execute(Pair(page, username), object : DisposableSingleObserver<Posts>(){
            override fun onSuccess(posts: Posts) {
                postsView.setPosts(posts.posts)
                postsView.hideInitProgressBar()
            }
            override fun onError(e: Throwable) {
                postsView.showGetPostsFailedMessage()
            }
        })
    }

    override fun onLoadMore() {
        getPostsUseCase.execute(Pair(++page, username), object : DisposableSingleObserver<Posts>(){
            override fun onSuccess(posts: Posts) {
                postsView.setPosts(posts.posts)
            }
            override fun onError(e: Throwable) {
                postsView.showGetPostsFailedMessage()
            }
        })    }

    override fun onClickPost(postId: Int) {
        postsView.moveToPost(postId)

    }
}