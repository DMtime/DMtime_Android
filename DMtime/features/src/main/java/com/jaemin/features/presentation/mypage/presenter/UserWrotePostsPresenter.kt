package com.jaemin.features.presentation.mypage.presenter

import com.jaemin.features.domain.usecase.GetUserPostsUseCase
import com.jaemin.features.presentation.mypage.contract.UserPostsContract

class UserWrotePostsPresenter(
    postsView: UserPostsContract.View,
    getUserPostsUseCase: GetUserPostsUseCase
) : UserPostsPresenter<GetUserPostsUseCase>(postsView,getUserPostsUseCase)