package com.jaemin.posts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.posts.databinding.FragmentPostsBinding


class PostsFragment : BaseFragment<FragmentPostsBinding>() {
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPostsBinding {
        return FragmentPostsBinding.inflate(inflater, container, false)
    }

}