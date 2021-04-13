package com.jaemin.posts.domain.repository

import com.jaemin.posts.domain.entity.PatchedPost
import com.jaemin.posts.domain.entity.Post
import com.jaemin.posts.domain.entity.Posts
import com.jaemin.posts.domain.entity.WrittenPost
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PostRepository {
    fun getPost(postId: Int): Single<Post>

    fun patchPost(postId: Int, patchedPost: PatchedPost): Completable

    fun writePost(writtenPost: WrittenPost): Completable

}