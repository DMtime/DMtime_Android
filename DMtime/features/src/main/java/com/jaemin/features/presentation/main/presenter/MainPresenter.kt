package com.jaemin.features.presentation.main.presenter

import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.usecase.DeleteLoginInfoUseCase
import com.jaemin.features.domain.usecase.GetAllGalleriesUseCase
import com.jaemin.features.domain.usecase.GetMyInfoUseCase
import com.jaemin.features.presentation.main.contract.MainContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver


class MainPresenter(
    private val mainView : MainContract.View,
    private val getMyInfoUseCase: GetMyInfoUseCase,
    private val getAllGalleriesUseCase: GetAllGalleriesUseCase,
    private val deleteLoginInfoUseCase: DeleteLoginInfoUseCase

) : MainContract.Presenter {

    override fun onCreate() {
        getMyInfoUseCase.execute(Unit, object : DisposableSingleObserver<User>(){
            override fun onSuccess(user: User) {
                mainView.setUserInfo(user)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })
        getAllGalleriesUseCase.execute(Unit, object : DisposableSingleObserver<List<Gallery>>(){
            override fun onSuccess(galleries: List<Gallery>) {
                mainView.setGalleries(galleries)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

        })

    }


    override fun onClickLogOut()  {
        if (deleteLoginInfoUseCase.execute())
            mainView.goToLogin()
    }

    override fun onClickGallery(galleryId: String) {
        mainView.goToGallery(galleryId)
    }

}