package com.jaemin.main.presentation

import com.jaemin.main.domain.entity.DefaultGallery

interface MainContract {
    interface View {
        fun setDefaultGalleries(defaultGalleries: List<DefaultGallery>)
        fun setDefaultGalleriesFailed()
        fun moveToPost(postId: Int)
    }

    interface Presenter {
        fun onCreate()

        fun onClickPost(postId: Int)

        fun onPause()

    }


}