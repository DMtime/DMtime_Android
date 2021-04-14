package com.jaemin.gallery.presentation.contract

interface PostContract {
    interface View{
        fun setPost()
    }
    interface Presenter{

        fun onClickLikeButton()

        fun onClickDislikeButton()

    }
}