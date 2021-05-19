package com.jaemin.features.presentation.login.presenter

import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.responsemodel.Token
import com.jaemin.features.domain.usecase.EmailValidateUseCase
import com.jaemin.features.domain.usecase.LoginUseCase
import com.jaemin.features.presentation.login.contract.LoginContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class LoginPresenter(private val loginView: LoginContract.View, private val loginUseCase: LoginUseCase) : LoginContract.Presenter {
    override fun onClickLoginButton() {
        if (EmailValidateUseCase.validateEmail(loginView.getEmail())) {
            loginUseCase.execute(Pair(loginView.getAutoLoginCheckBoxState(),LoginInfo(loginView.getEmail(), loginView.getPassword())), object : DisposableSingleObserver<Token>() {
                override fun onSuccess(result: Token) {
                    loginView.goToMain()
                }

                override fun onError(e: Throwable) {
                    loginView.showIncorrectEmailMessage()
                }
            })
        }
        else{
            loginView.showIncorrectEmailMessage()
        }

    }
}