package com.jaemin.posts.data.repository

import com.jaemin.posts.data.dto.request.toData
import com.jaemin.posts.data.dto.response.toEntity
import com.jaemin.posts.data.remote.PostsApi
import com.jaemin.posts.domain.entity.PatchedPost
import com.jaemin.posts.domain.entity.Post
import com.jaemin.posts.domain.entity.Posts
import com.jaemin.posts.domain.entity.WrittenPost
import com.jaemin.posts.domain.repository.PostsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class PostsRepositoryImpl(private val postsApi: PostsApi) : PostsRepository {

    override fun getPosts(perPage: Int, page: Int, galleryId: Int): Single<Posts> =
        postsApi.getGalleryPosts(perPage,page,galleryId).map { it.toEntity() }
}