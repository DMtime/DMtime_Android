package com.jaemin.features.presentation.main.presenter

import com.jaemin.features.domain.requestmodel.AddGallery
import com.jaemin.features.domain.usecase.AddGalleryUseCase
import com.jaemin.features.presentation.main.contract.AddGalleryContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class AddGalleryPresenter(
    private val addGalleryView: AddGalleryContract.View,
    private val addGalleryUseCase: AddGalleryUseCase
) :
    AddGalleryContract.Presenter {
    override fun onClickAddGalleryButton() {
        addGalleryUseCase.execute(
            AddGallery(
                2,
                addGalleryView.getGalleryId(),
                addGalleryView.getGalleryExplain(),
                addGalleryView.getGalleryName()
            )
        , object : DisposableSingleObserver<Unit>(){
                override fun onSuccess(t: Unit) {
                    addGalleryView.showAddGallerySuccessMessage()
                    addGalleryView.goToMain()
                }

                override fun onError(e: Throwable) {

                }
            })

    }
}