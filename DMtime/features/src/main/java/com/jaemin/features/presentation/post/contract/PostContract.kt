package com.jaemin.features.presentation.post.contract

import com.jaemin.features.domain.entity.Post

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