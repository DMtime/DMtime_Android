package com.jaemin.gallery.presentation.contract

import com.jaemin.gallery.domain.entity.Posts

interface GalleryContract {
    interface View{
        fun setPosts(posts: Posts)

        fun showGetPostsFailedMessage()


        fun getGalleryId() : String

        fun moveToPost()
    }

    interface Presenter{
        fun onCreate()

        fun onClickPost(postId: Int)

        fun onClickGallery()


    }
}