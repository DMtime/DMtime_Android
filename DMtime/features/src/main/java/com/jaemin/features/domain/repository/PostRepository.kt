package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.PatchedPost
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.WrittenPost
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.io.File

interface PostRepository {
    fun getPost(postId: Int): Single<Post>

    fun patchPost(postId: Int, patchedPost: PatchedPost): Completable

    fun writePost(writtenPost: WrittenPost): Single<Boolean>

}