package com.jaemin.dmtime.di

import com.jaemin.features.domain.usecase.GetUserLikedPostsUseCase
import com.jaemin.features.domain.usecase.GetUserPostsUseCase
import com.jaemin.features.domain.usecase.GetUserWroteCommentPostsUseCase
import com.jaemin.features.domain.usecase.UserPostsUseCase
import com.jaemin.features.presentation.mypage.contract.MyPageContract
import com.jaemin.features.presentation.mypage.contract.UserPostsContract
import com.jaemin.features.presentation.mypage.presenter.*
import org.koin.dsl.module

val myPageModule = module{
    factory<MyPageContract.Presenter> {(view : MyPageContract.View)-> MyPagePresenter(view, get(), get()) }
    factory {(view : UserPostsContract.View)-> UserWrotePostsPresenter(view, get()) }
    factory {(view : UserPostsContract.View)-> UserWroteCommentsPostsPresenter(view, get()) }
    factory {(view : UserPostsContract.View)-> UserLikedPostsPresenter(view, get()) }


}