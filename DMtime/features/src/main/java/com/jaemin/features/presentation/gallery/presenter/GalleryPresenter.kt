package com.jaemin.features.presentation.gallery.presenter

import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.usecase.GetPostsUseCase
import com.jaemin.features.presentation.gallery.contract.GalleryContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class GalleryPresenter(
    private val galleryView: GalleryContract.View,
    private val getPostsUseCase: GetPostsUseCase
) : GalleryContract.Presenter {
    private var page = 1
    private val galleryId by lazy {
        galleryView.getGalleryId()
    }
    override fun onCreate() {
        page = 1
        getPostsUseCase.execute(Pair(page, galleryId), object : DisposableSingleObserver<Posts>(){
            override fun onSuccess(posts: Posts) {
                galleryView.setPosts(posts.posts)
                galleryView.hideInitProgressBar()
            }
            override fun onError(e: Throwable) {
                galleryView.showGetPostsFailedMessage()
            }
        })
    }

    override fun onLoadMore() {
        getPostsUseCase.execute(Pair(++page, galleryId), object : DisposableSingleObserver<Posts>(){
            override fun onSuccess(posts: Posts) {
                galleryView.loadPosts(posts.posts)
            }
            override fun onError(e: Throwable) {
                galleryView.showGetPostsFailedMessage()
            }
        })    }

    override fun onDestroy() {
        getPostsUseCase.dispose()
    }

    override fun onClickPost(postId: Int) {
        galleryView.moveToPost(postId)

    }

    override fun onRefresh() {
        page=1
        onCreate()
    }


}