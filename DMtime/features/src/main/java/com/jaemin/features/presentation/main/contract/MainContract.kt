package com.jaemin.features.presentation.main.contract

import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User

interface MainContract {
    interface View {
        fun setGalleries(galleries: List<Gallery>)

        fun setUserInfo(user: User)
    }

    interface Presenter {
        fun onCreate()

        fun onClickGalleryMenu(menuId : Int)
    }


}