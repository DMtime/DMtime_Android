package com.jaemin.gallery.presentation.contract

import com.jaemin.gallery.domain.entity.Post

interface PostContract {
    interface View {

        fun setPost(post: Post)


        fun showErrorScreen()

    }

    interface Presenter {
        fun onCreate(postId: Int)

        fun onClickLikeButton()

        fun onClickDislikeButton()

    }
}