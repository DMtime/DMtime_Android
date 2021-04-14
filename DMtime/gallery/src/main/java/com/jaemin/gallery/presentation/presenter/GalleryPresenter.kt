package com.jaemin.gallery.presentation.presenter

import com.jaemin.gallery.domain.entity.Posts
import com.jaemin.gallery.domain.usecase.GetPostsUseCase
import com.jaemin.gallery.presentation.contract.GalleryContract
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
        getPostsUseCase.execute(Pair(page, galleryId), object : DisposableSingleObserver<Posts>(){
            override fun onSuccess(posts: Posts) {
                galleryView.setPosts(posts)
            }
            override fun onError(e: Throwable) {
                galleryView.showGetPostsFailedMessage()
            }
        })
    }

    override fun onClickPost(postId: Int) {

    }

    override fun onClickGallery() {
    }

}