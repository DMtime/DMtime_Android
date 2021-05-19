package com.jaemin.features.presentation.main.contract

interface AddGalleryContract {
    interface View{

        fun getGalleryName() : String

        fun getGalleryId() : String

        fun getGalleryExplain() : String

        fun goToMain()

        fun showAddGallerySuccessMessage()
    }
    interface Presenter{
        fun onClickAddGalleryButton()

    }
}