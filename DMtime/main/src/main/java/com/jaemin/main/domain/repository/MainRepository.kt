package com.jaemin.main.domain.repository

import com.jaemin.main.data.dto.response.DefaultGalleryResponse
import com.jaemin.main.domain.entity.DefaultGallery
import io.reactivex.rxjava3.core.Single

interface MainRepository {
    fun getDefaultGalleries() : Single<List<DefaultGallery>>

}