package com.jaemin.features.presentation.mypage.presenter

import com.jaemin.features.domain.usecase.GetUserLikedPostsUseCase
import com.jaemin.features.domain.usecase.GetUserPostsUseCase
import com.jaemin.features.presentation.mypage.contract.UserPostsContract

class UserLikedPostsPresenter(
    postsView: UserPostsContract.View,
    getUserLikedPostsUseCase: GetUserLikedPostsUseCase
) : UserPostsPresenter<GetUserLikedPostsUseCase>(postsView,getUserLikedPostsUseCase)