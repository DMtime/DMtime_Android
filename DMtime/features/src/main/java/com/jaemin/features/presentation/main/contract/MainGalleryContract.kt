package com.jaemin.features.presentation.main.contract

import com.jaemin.features.domain.entity.DefaultGallery

interface MainGalleryContract {
    interface View {
        fun setDefaultGalleries(defaultGalleries: List<DefaultGallery>)

        fun setDefaultGalleriesFailed()

        fun moveToPost(postId: Int)

        fun moveToGallery(galleryId: String)

        fun showProgressBar()

        fun hideProgressBar()

    }

    interface Presenter {
        fun onCreate()

        fun onClickPost(postId: Int)

        fun onClickGallery(galleryId: String)

        fun onPause()

        fun onDestroy()


    }


}