package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User
import io.reactivex.rxjava3.core.Single

interface MainRepository {
    fun getDefaultGalleries() : Single<List<DefaultGallery>>

    fun getAllGalleries() : Single<List<Gallery>>

    fun getMyInfo() : Single<User>
}