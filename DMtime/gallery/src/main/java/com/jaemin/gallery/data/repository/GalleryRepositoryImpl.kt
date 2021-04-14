package com.jaemin.gallery.data.repository

import com.jaemin.gallery.data.dto.response.toEntity
import com.jaemin.gallery.data.remote.PostsApi
import com.jaemin.gallery.domain.entity.Posts
import com.jaemin.gallery.domain.repository.GalleryRepository
import io.reactivex.rxjava3.core.Single

class GalleryRepositoryImpl(private val postsApi: PostsApi) : GalleryRepository {

    override fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts> =
        postsApi.getGalleryPosts(perPage,page,galleryId).map { it.toEntity() }
}