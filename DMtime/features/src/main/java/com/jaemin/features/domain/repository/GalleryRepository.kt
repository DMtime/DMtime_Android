package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.requestmodel.AddGallery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GalleryRepository {

    fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts>

    fun addGallery(addGallery: AddGallery): Completable

}