package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.PatchedPost
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.Posts
import com.jaemin.features.domain.entity.WrittenPost
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface PostsRepository {
    fun getUserLikedPosts(page : Int, username : String) : Single<Posts>

    fun getUserWroteCommentPosts(page : Int, username : String) : Single<Posts>

    fun getUserWrotePosts(page : Int, username : String) : Single<Posts>

    fun getMyLikedPosts(page : Int) : Single<Posts>

    fun getMyWroteCommentPosts(page : Int) : Single<Posts>
}