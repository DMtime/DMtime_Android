package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.request.toData
import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.GalleryApi
import com.jaemin.features.data.remote.PostsApi
import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.repository.GalleryRepository
import com.jaemin.features.domain.requestmodel.AddGallery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GalleryRepositoryImpl(private val postsApi: PostsApi, private val galleryApi: GalleryApi) : GalleryRepository {

    override fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts> =
        postsApi.getGalleryPosts(perPage,page,galleryId).map { it.toEntity() }

    override fun addGallery(addGallery: AddGallery): Completable =
        galleryApi.addGallery(addGallery.toData())
}