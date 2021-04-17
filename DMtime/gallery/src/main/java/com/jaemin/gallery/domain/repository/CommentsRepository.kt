package com.jaemin.gallery.domain.repository

import com.jaemin.gallery.domain.entity.Comments
import io.reactivex.rxjava3.core.Single

interface CommentsRepository {
    fun getComments(postId : Int) : Single<Comments>

}