package com.jaemin.gallery.presentation.contract

import com.jaemin.gallery.domain.entity.PostPreview

interface GalleryContract {
    interface View{
        fun setPosts(posts: List<PostPreview>)

        fun showGetPostsFailedMessage()

        fun getGalleryId() : String

        fun moveToPost()
    }

    interface Presenter{
        fun onCreate()

        fun onClickPost(postId: Int)


    }
}