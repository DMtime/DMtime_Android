package com.jaemin.features.presentation.main.presenter

import android.util.Log
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.features.presentation.main.contract.MainContract
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
                    e.printStackTrace()
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
        getDefaultGalleriesUseCase.clear()
    }

    override fun onDestroy() {
        getDefaultGalleriesUseCase.dispose()
    }
}