package com.jaemin.features.presentation.splash.presenter

import com.jaemin.features.domain.usecase.AutoLoginUseCase
import com.jaemin.features.domain.usecase.DeleteLoginInfoUseCase
import com.jaemin.features.presentation.splash.contract.AutoLoginContract

class AutoLoginPresenter(
    private val autoLoginView: AutoLoginContract.View,
    private val autoLoginUseCase: AutoLoginUseCase,
    private val deleteLoginInfoUseCase: DeleteLoginInfoUseCase
    ) : AutoLoginContract.Presenter {
    override fun onCreate() {
        if (autoLoginUseCase.execute()) {
            autoLoginView.goToMain()
        }else{
            deleteLoginInfoUseCase.execute()
        }
    }
}