package com.jaemin.features.data.repository

import android.util.Log
import com.jaemin.features.data.dto.request.WrittenPostRequest
import com.jaemin.features.data.dto.request.toData
import com.jaemin.features.data.remote.PostApi
import com.jaemin.features.domain.entity.PatchedPost
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.domain.repository.PostRepository
import com.jaemin.features.data.dto.response.toEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PostRepositoryImpl(private val postApi: PostApi) : PostRepository {
    override fun getPost(postId: Int): Single<Post> = postApi.getPost(postId).map { it.toEntity() }

    override fun patchPost(postId: Int, patchedPost: PatchedPost): Completable =
        postApi.patchPost(postId, patchedPost.toData())

    override fun writePost(writtenPost: Pair<String, WrittenPost>): Single<Boolean> {

        return Observable.fromIterable(writtenPost.second.images.map {
            val fileToRequestBody = it.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", it.name, fileToRequestBody)
        }).flatMap { postApi.postImage(it).toObservable() }.toList()
            .flatMap {
                postApi.writePost(writtenPost.first,
                    WrittenPostRequest(
                        writtenPost.second.isAnonymous,
                        it.map { image -> image.image },
                        writtenPost.second.content,
                        writtenPost.second.title
                    )
                )
                    .toSingleDefault(true)
                    .onErrorReturnItem(false)
            }
    }

    override fun postLike(postId: Int): Completable = postApi.postLike(postId)

    override fun postDislike(postId: Int): Completable = postApi.postDislike(postId)

    override fun postLikeCancel(postId: Int): Completable = postApi.postLikeCancel(postId)

    override fun postDislikeCancel(postId: Int): Completable = postApi.postDislikeCancel(postId)

}