package com.jaemin.features.presentation.post.presenter

import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.domain.usecase.WritePostsUseCase
import com.jaemin.features.presentation.post.contract.WritePostContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import splitties.toast.toast

class WritePostPresenter(
    private val writePostView : WritePostContract.View,
    private val writePostsUseCase: WritePostsUseCase) : WritePostContract.Presenter {

    override fun onClickAttachImageButton() {
        writePostView.requestStoragePermission()
    }

    override fun onClickWritePostButton(writtenPost: WrittenPost) {
        writePostsUseCase.execute(writtenPost, object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(t: Boolean) {
                writePostView.showSuccessMessage()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                writePostView.showErrorMessage()
            }

        })
    }
}