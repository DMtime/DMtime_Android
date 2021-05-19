package com.jaemin.features.presentation.main.contract

import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User

interface MainContract {
    interface View {
        fun setGalleries(galleries: List<Gallery>)

        fun setUserInfo(user: User)

        fun goToLogin()

        fun goToGallery(galleryId : String)
    }

    interface Presenter {
        fun onCreate()

        fun onClickLogOut()

        fun onClickGallery(galleryId: String)
    }


}