package com.jaemin.features.presentation.splash.contract

interface AutoLoginContract {
    interface View{
        fun goToMain()
    }
    interface Presenter{
        fun onCreate()
    }
}