package com.jaemin.features.presentation.login.contract

import com.jaemin.features.domain.requestmodel.LoginInfo

interface LoginContract {
    interface View{
        fun goToMain()

        fun getEmail() : String

        fun getPassword() : String

        fun showIncorrectEmailMessage()



    }
    interface Presenter{
        fun onClickLoginButton()
    }
}