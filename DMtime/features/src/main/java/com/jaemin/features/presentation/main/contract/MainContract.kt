package com.jaemin.features.presentation.main.contract

import com.jaemin.features.domain.entity.DefaultGallery

interface MainContract {
    interface View {
        fun setDefaultGalleries(defaultGalleries: List<DefaultGallery>)
        fun setDefaultGalleriesFailed()
        fun moveToPost(postId: Int)
        fun moveToGallery(galleryId: String)

    }

    interface Presenter {
        fun onCreate()

        fun onClickPost(postId: Int)

        fun onClickGallery(galleryId: String)

        fun onPause()

        fun onDestroy()


    }


}