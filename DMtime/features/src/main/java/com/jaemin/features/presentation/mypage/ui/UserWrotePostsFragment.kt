package com.jaemin.features.presentation.mypage.ui

import com.jaemin.features.domain.usecase.GetUserPostsUseCase
import com.jaemin.features.presentation.mypage.presenter.UserWrotePostsPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class UserWrotePostsFragment :UserPostsFragment<UserWrotePostsPresenter,GetUserPostsUseCase>() {
    override val userPostsPresenter: UserWrotePostsPresenter by inject { parametersOf(this)  }
}