package com.jaemin.features.presentation.signup.presenter

import com.jaemin.features.domain.usecase.EmailVerificationUseCase
import com.jaemin.features.presentation.signup.contract.EmailVerificationContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class EmailVerificationPresenter(private val emailVerificationView : EmailVerificationContract.View,
                                 private val emailVerificationUseCase: EmailVerificationUseCase) : EmailVerificationContract.Presenter{
    override fun onSignUpClicked() {
        emailVerificationUseCase.execute(emailVerificationView.getVerificationCode(), object : DisposableSingleObserver<Unit>(){
            override fun onSuccess(t: Unit) {
                emailVerificationView.goToLogin()
            }
            override fun onError(e: Throwable?) {

            }
        })
    }

}