package com.jaemin.features.presentation.mypage.presenter

import com.jaemin.features.domain.usecase.GetUserPostsUseCase
import com.jaemin.features.domain.usecase.GetUserWroteCommentPostsUseCase
import com.jaemin.features.presentation.mypage.contract.UserPostsContract

class UserWroteCommentsPostsPresenter(
    postsView: UserPostsContract.View,
    getUserWroteCommentPostsUseCase: GetUserWroteCommentPostsUseCase
) : UserPostsPresenter<GetUserWroteCommentPostsUseCase>(postsView,getUserWroteCommentPostsUseCase)