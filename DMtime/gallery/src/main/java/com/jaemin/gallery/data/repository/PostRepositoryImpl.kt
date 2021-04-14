package com.jaemin.gallery.data.repository

import com.jaemin.gallery.data.dto.request.toData
import com.jaemin.gallery.data.remote.PostApi
import com.jaemin.gallery.domain.entity.PatchedPost
import com.jaemin.gallery.domain.entity.Post
import com.jaemin.gallery.domain.entity.WrittenPost
import com.jaemin.gallery.domain.repository.PostRepository
import com.jaemin.gallery.data.dto.response.toEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class PostRepositoryImpl(private val postApi: PostApi) : PostRepository {
    override fun getPost(postId: Int): Single<Post> = postApi.getPost(postId).map { it.toEntity() }

    override fun patchPost(postId: Int, patchedPost: PatchedPost): Completable =postApi.patchPost(postId, patchedPost.toData())

    override fun writePost(writtenPost: WrittenPost): Completable = postApi.writePost(writtenPost.toData())

}