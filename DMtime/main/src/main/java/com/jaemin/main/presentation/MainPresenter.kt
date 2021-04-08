package com.jaemin.main.presentation

import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.usecase.GetDefaultGalleriesUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver


class MainPresenter(
    private val mainView: MainContract.View,
    private val getDefaultGalleriesUseCase: GetDefaultGalleriesUseCase,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MainContract.Presenter {

    override fun onCreate() {
        getDefaultGalleriesUseCase.execute().subscribeWith(object :DisposableSingleObserver<List<DefaultGallery>>(){
            override fun onSuccess(t: List<DefaultGallery>) {
                mainView.setDefaultGalleries(t)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }

    override fun onPause() {
        compositeDisposable.clear()
    }
}