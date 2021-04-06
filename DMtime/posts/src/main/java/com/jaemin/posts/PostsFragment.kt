package com.jaemin.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.posts.databinding.FragmentPostsBinding


class PostsFragment : BaseFragment<FragmentPostsBinding>() {
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPostsBinding {
        return FragmentPostsBinding.inflate(inflater, container, false)
    }

}