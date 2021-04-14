package com.jaemin.posts.data.repository

import com.jaemin.posts.data.remote.PostsApi
import io.reactivex.rxjava3.core.Single

class PostsRepositoryImpl(private val postsApi: PostsApi) : PostsRepository {

    override fun getPosts(perPage: Int, page: Int, galleryId: String): Single<Posts> =
        postsApi.getGalleryPosts(perPage,page,galleryId).map { it.toEntity() }
}