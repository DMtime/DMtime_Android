package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.Comments
import io.reactivex.rxjava3.core.Single

interface CommentsRepository {
    fun getComments(postId : Int) : Single<Comments>

}