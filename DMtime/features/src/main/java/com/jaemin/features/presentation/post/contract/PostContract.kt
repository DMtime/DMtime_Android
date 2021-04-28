package com.jaemin.features.presentation.post.contract

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

    }

    interface Presenter {
        fun onCreate()

        fun onClickLikeButton()

        fun onClickDislikeButton()

        fun onClickPostImage(position : Int)
    }
}