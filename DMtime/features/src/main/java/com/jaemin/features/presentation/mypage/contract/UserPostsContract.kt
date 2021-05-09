package com.jaemin.features.presentation.mypage.contract


interface UserPostsContract : PostsContract {
    interface View : PostsContract.View{
        fun getUsername() : String

    }
    interface Presenter : PostsContract.Presenter
}