package com.jaemin.features.presentation.mypage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.base.BaseFragment
import com.jaemin.features.databinding.FragmentUserWrotePostsBinding
import com.jaemin.features.domain.usecase.GetUserLikedPostsUseCase
import com.jaemin.features.presentation.gallery.adapter.PostsAdapter
import com.jaemin.features.presentation.mypage.contract.UserPostsContract
import com.jaemin.features.presentation.mypage.presenter.UserLikedPostsPresenter
import com.jaemin.features.presentation.mypage.presenter.UserPostsPresenter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class UserLikedPostsFragment : UserPostsFragment<UserLikedPostsPresenter,GetUserLikedPostsUseCase>() {
    override val userPostsPresenter: UserLikedPostsPresenter by inject { parametersOf(this) }
}