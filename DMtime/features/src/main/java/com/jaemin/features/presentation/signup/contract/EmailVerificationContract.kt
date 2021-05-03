package com.jaemin.features.presentation.signup.contract

interface EmailVerificationContract {
    interface View{
        fun showIncorrectVerificationCodeMessage()

        fun getVerificationCode() : String

        fun goToLogin()
    }

    interface Presenter{
        fun onSignUpClicked()
    }
}