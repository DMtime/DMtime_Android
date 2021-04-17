package com.jaemin.main.presentation

import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.usecase.GetDefaultGalleriesUseCase
import io.reactivex.rxjava3.observers.DisposableSingleObserver


class MainPresenter(
    private val mainView: MainContract.View,
    private val getDefaultGalleriesUseCase: GetDefaultGalleriesUseCase,
) : MainContract.Presenter {

    override fun onCreate() {
        getDefaultGalleriesUseCase.execute(
            Unit,
            object : DisposableSingleObserver<List<DefaultGallery>>() {
                override fun onSuccess(galleries: List<DefaultGallery>) {
                    mainView.setDefaultGalleries(galleries)
                }

                override fun onError(e: Throwable) {
                    mainView.setDefaultGalleriesFailed()
                }
            })
    }

    override fun onClickPost(postId: Int) {
        mainView.moveToPost(postId)
    }

    override fun onClickGallery(galleryId: String) {
        mainView.moveToGallery(galleryId)
    }

    override fun onPause() {
        getDefaultGalleriesUseCase.dispose()
    }
}