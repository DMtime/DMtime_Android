package com.jaemin.features.presentation.main.presenter

import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.features.presentation.main.contract.MainGalleryContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver


class MainGalleryPresenter(
    private val mainGalleryView: MainGalleryContract.View,
    private val getDefaultGalleriesUseCase: GetDefaultGalleriesUseCase,
) : MainGalleryContract.Presenter {

    override fun onCreate() {
        mainGalleryView.showProgressBar()
        getDefaultGalleriesUseCase.execute(
            Unit,
            object : DisposableSingleObserver<List<DefaultGallery>>() {
                override fun onSuccess(galleries: List<DefaultGallery>) {
                    mainGalleryView.setDefaultGalleries(galleries)
                    mainGalleryView.hideProgressBar()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    mainGalleryView.setDefaultGalleriesFailed()
                }
            })
    }

    override fun onClickPost(postId: Int) {
        mainGalleryView.moveToPost(postId)
    }

    override fun onClickGallery(galleryId: String) {
        mainGalleryView.moveToGallery(galleryId)
    }

    override fun onPause() {
        getDefaultGalleriesUseCase.clear()
    }

    override fun onDestroy() {
        getDefaultGalleriesUseCase.dispose()
    }
}