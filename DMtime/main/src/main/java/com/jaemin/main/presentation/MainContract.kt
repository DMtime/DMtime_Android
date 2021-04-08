package com.jaemin.main.presentation

import com.jaemin.main.data.dto.response.DefaultGalleryResponse
import com.jaemin.main.domain.entity.DefaultGallery
import io.reactivex.rxjava3.core.Single

interface MainContract {
    interface View{
        fun setDefaultGalleries(defaultGalleries : List<DefaultGallery>)

    }
    interface Presenter{
        fun onCreate()

        fun onPause()

    }


}