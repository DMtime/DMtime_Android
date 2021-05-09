package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.PostsApi
import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.repository.PostsRepository
import io.reactivex.rxjava3.core.Single

class PostsRepositoryImpl(private val postsApi: PostsApi) : PostsRepository {
    override fun getUserLikedPosts(page: Int, username: String): Single<Posts> =
        postsApi.getUserLikedPosts(username, 15, page).map { it.toEntity() }

    override fun getUserWroteCommentPosts(page: Int, username: String): Single<Posts> =
        postsApi.getUserWroteCommentPosts(username, 15,page ).map { it.toEntity() }

    override fun getUserWrotePosts(page: Int, username: String): Single<Posts> =
        postsApi.getUserPosts(15, page, username).map { it.toEntity() }

    override fun getMyLikedPosts(page: Int): Single<Posts> =
        postsApi.getMyLikedPosts(15, page).map { it.toEntity() }

    override fun getMyWroteCommentPosts(page: Int): Single<Posts> =
        postsApi.getMyWroteCommentPosts(15, page).map { it.toEntity() }


}