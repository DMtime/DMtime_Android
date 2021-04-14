package com.jaemin.gallery.domain.repository

import com.jaemin.gallery.domain.entity.Posts
import io.reactivex.rxjava3.core.Single

interface GalleryRepository {

    fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts>


}