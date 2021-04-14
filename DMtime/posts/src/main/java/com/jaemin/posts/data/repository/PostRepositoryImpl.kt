package com.jaemin.posts.data.repository

import com.jaemin.posts.data.dto.request.toData
import com.jaemin.posts.data.dto.response.toEntity
import com.jaemin.posts.data.remote.PostsApi
import com.jaemin.posts.domain.entity.PatchedPost
import com.jaemin.posts.domain.entity.Post
import com.jaemin.posts.domain.entity.WrittenPost
import com.jaemin.posts.domain.repository.PostRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class PostRepositoryImpl(private val postsApi: PostsApi) : PostRepository {
    override fun getPost(postId: Int): Single<Post> = postsApi.getPost(postId).map { it.toEntity() }

    override fun patchPost(postId: Int, patchedPost: PatchedPost): Completable =postsApi.patchPost(postId, patchedPost.toData())

    override fun writePost(writtenPost: WrittenPost): Completable = postsApi.writePost(writtenPost.toData())

}