package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.DefaultGallery
import io.reactivex.rxjava3.core.Single

interface MainRepository {
    fun getDefaultGalleries() : Single<List<DefaultGallery>>

}