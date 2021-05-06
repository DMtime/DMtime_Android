package com.jaemin.features.presentation.signup.presenter

import com.jaemin.features.domain.requestmodel.SignUpInfo
import com.jaemin.features.domain.usecase.*
import com.jaemin.features.presentation.signup.contract.SignUpContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber

class SignUpPresenter(private val signUpView: SignUpContract.View,
                      private val signUpUseCase: SignUpUseCase,
                      private val duplicateEmailUseCase: DuplicateEmailUseCase,
                      private val duplicateUsernameUseCase: DuplicateUsernameUseCase) : SignUpContract.Presenter {
    override fun onClickNextButton() {
        signUpUseCase.execute(SignUpInfo(signUpView.getUsername(), signUpView.getPassword(), signUpView.getEmail()), object : DisposableSingleObserver<Unit>() {
            override fun onSuccess(t: Unit) {
                signUpView.goToEmailVerification()
            }
            override fun onError(e: Throwable) {

            }

        })
    }

    override fun onClickDuplicateUsernameCheckButton() {
        if (signUpView.getUsername().length < 2 || signUpView.getUsername().length > 20) {
            signUpView.showUsernameWordsErrorMessage()
            signUpUseCase.isUsernameValidated = false
        } else {
            duplicateUsernameUseCase.execute(signUpView.getUsername(), object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(result: Boolean) {
                    if (result) {
                        signUpView.showAvailableUsernameMessage()
                        signUpUseCase.isUsernameValidated = true
                    } else {
                        signUpView.showDuplicateUsernameErrorMessage()
                        signUpUseCase.isUsernameValidated = false
                    }
                }

                override fun onError(e: Throwable) {

                }

            })
        }
        checkAllValueIsValidated()

    }

    override fun onClickDuplicateEmailCheckButton() {
        duplicateEmailUseCase.execute(signUpView.getEmail(), object : DisposableSingleObserver<Boolean>() {
            override fun onSuccess(result: Boolean) {
                if (result) {
                    signUpView.showAvailableEmailMessage()
                    signUpUseCase.isEmailValidated = true
                }
                else{
                    signUpView.showDuplicateEmailErrorMessage()
                }
            }

            override fun onError(e: Throwable) {
                signUpUseCase.isEmailValidated = false
            }

        })
        checkAllValueIsValidated()

    }

    override fun onTypingEmailEnd(email: String) {
        if (!EmailValidateUseCase.validateEmail(email)) {
            signUpView.showIncorrectEmailErrorMessage()
        } else {
            signUpView.initEmailMessage()
        }
        signUpUseCase.isEmailValidated = false
        checkAllValueIsValidated()
    }

    override fun onTypingUsernameEnd(username: String) {
        if (signUpView.getUsername().length < 2 || signUpView.getUsername().length > 20) {
            signUpView.showUsernameWordsErrorMessage()
        } else {
            signUpView.initUsernameMessage()
        }
        signUpUseCase.isPasswordValidated = false
        checkAllValueIsValidated()
    }

    override fun onTypingPasswordEnd(password: String) {
        if (!PasswordValidateUseCase.validatePassword(signUpView.getPassword())) {
            signUpView.showIncorrectPasswordErrorMessage()
            signUpUseCase.isPasswordValidated = false
        } else {
            signUpView.showAvailablePasswordMessage()
            signUpUseCase.isPasswordValidated = true

        }
        checkAllValueIsValidated()
    }

    override fun onTypingPasswordConfirmEnd(passwordConfirm: String) {
        if (passwordConfirm != signUpView.getPassword()) {
            signUpView.showNotEqualPasswordErrorMessage()
            signUpUseCase.isPasswordConfirmValidated = false
        } else {
            signUpView.initPasswordConfirmMessage()
            signUpUseCase.isPasswordConfirmValidated = true
        }
        checkAllValueIsValidated()
    }

    private fun checkAllValueIsValidated() {
        if (signUpUseCase.isAllValueValidated()) {
            Timber.d("enable")
            signUpView.enableNextButton()
        }
        else {
            Timber.d("disable")
            signUpView.disableNextButton()
        }
    }


}