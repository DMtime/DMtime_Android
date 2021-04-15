package com.jaemin.gallery.presentation.contract

import com.jaemin.gallery.domain.entity.Post

interface PostContract {
    interface View {

        fun setPost(post: Post)

        fun getPostId(): Int

        fun showErrorScreen()

    }

    interface Presenter {
        fun onCreate()

        fun onClickLikeButton()

        fun onClickDislikeButton()

    }
}