package com.jaemin.posts.domain.repository

import com.jaemin.posts.domain.entity.Posts
import io.reactivex.rxjava3.core.Single

interface PostsRepository {

    fun getPosts(perPage: Int, page: Int, galleryId: Int): Single<Posts>


}