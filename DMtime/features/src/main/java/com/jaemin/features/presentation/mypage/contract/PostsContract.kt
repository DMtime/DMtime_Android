package com.jaemin.features.presentation.mypage.contract

import com.jaemin.features.domain.entity.PostPreview

interface PostsContract {
    interface View{
        fun setPosts(posts: List<PostPreview>)

        fun showGetPostsFailedMessage()

        fun moveToPost(postId : Int)

        fun showProgressBar()

        fun hideProgressBar()

        fun hideInitProgressBar()
    }
    interface Presenter{
        fun onClickPost(postId : Int)

        fun onCreate()

        fun onLoadMore()
    }
}