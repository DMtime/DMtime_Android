package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.PostsApi
import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.repository.GalleryRepository
import io.reactivex.rxjava3.core.Single

class GalleryRepositoryImpl(private val postsApi: PostsApi) : GalleryRepository {

    override fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts> =
        postsApi.getGalleryPosts(perPage,page,galleryId).map { it.toEntity() }
}