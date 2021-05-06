package com.jaemin.features.presentation.gallery.contract

import com.jaemin.features.domain.entity.PostPreview

interface GalleryContract {
    interface View{
        fun setPosts(posts: List<PostPreview>)

        fun showGetPostsFailedMessage()

        fun getGalleryId() : String

        fun moveToPost(postId : Int)

        fun showProgressBar()

        fun hideProgressBar()

        fun hideInitProgressBar()

    }

    interface Presenter{
        fun onCreate()

        fun onLoadMore()

        fun onClickPost(postId: Int)


    }
}