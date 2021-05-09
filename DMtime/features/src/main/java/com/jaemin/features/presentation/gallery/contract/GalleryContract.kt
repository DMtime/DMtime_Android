package com.jaemin.features.presentation.gallery.contract

import com.jaemin.features.domain.entity.PostPreview
import com.jaemin.features.presentation.mypage.contract.PostsContract

interface GalleryContract {
    interface View: PostsContract.View{
        fun getGalleryId() : String
    }

    interface Presenter : PostsContract.Presenter
}