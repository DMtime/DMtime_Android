package com.jaemin.main.presentation

import com.jaemin.main.domain.entity.DefaultGallery

interface MainContract {
    interface View {
        fun setDefaultGalleries(defaultGalleries: List<DefaultGallery>)
        fun setDefaultGalleriesFailed()

    }

    interface Presenter {
        fun onCreate()

        fun onPause()

    }


}