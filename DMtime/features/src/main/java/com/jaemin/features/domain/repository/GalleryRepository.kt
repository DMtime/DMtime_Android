package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.Posts
import io.reactivex.rxjava3.core.Single

interface GalleryRepository {

    fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts>


}