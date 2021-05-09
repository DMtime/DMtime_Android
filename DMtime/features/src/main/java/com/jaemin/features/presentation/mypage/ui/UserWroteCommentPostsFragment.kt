package com.jaemin.features.presentation.mypage.ui

import com.jaemin.features.domain.usecase.GetUserWroteCommentPostsUseCase
import com.jaemin.features.presentation.mypage.presenter.UserWroteCommentsPostsPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class UserWroteCommentPostsFragment : UserPostsFragment<UserWroteCommentsPostsPresenter,GetUserWroteCommentPostsUseCase>() {
    override val userPostsPresenter: UserWroteCommentsPostsPresenter by inject { parametersOf(this) }
}