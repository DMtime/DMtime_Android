package com.jaemin.features.presentation.post.contract

import com.jaemin.base.BasePresenter
import com.jaemin.features.domain.entity.Post

interface PostContract {
    interface View {

        fun setPost(post: Post)

        fun showImage(position: Int)

        fun getPostId() : Int

        fun showErrorScreen()

        fun setPostLike(likes : Int)

        fun setPostDislike(dislikes :Int)

        fun enablePostLikeButton()

        fun enablePostDislikeButton()

        fun disablePostLikeButton()

        fun disablePostDislikeButton()

        fun showDeleteButton()

        fun showDeleteSuccessMessage()

        fun goToGallery()
    }

    interface Presenter : BasePresenter {
        fun onCreate()

        fun onClickLikeButton()

        fun onClickDislikeButton()

        fun onClickPostDelete()

        fun onClickPostImage(position : Int)
    }
}