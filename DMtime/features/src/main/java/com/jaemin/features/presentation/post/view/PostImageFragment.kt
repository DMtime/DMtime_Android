package com.jaemin.features.presentation.post.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseDialogFragment
import com.jaemin.features.databinding.FragmentPostImageBinding
import com.jaemin.features.presentation.post.adapter.PostImageViewPagerAdapter


class PostImageFragment : BaseDialogFragment<FragmentPostImageBinding>() {
    private lateinit var postImageAdapter: PostImageViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postImageAdapter = PostImageViewPagerAdapter()
        binding.imagePager.adapter = postImageAdapter
        postImageAdapter.updateItems(requireArguments().getStringArrayList("images")!!)
        binding.imagePager.currentItem = requireArguments().getInt("position")

        binding.previousImg.setOnClickListener {
            binding.imagePager.currentItem = binding.imagePager.currentItem-1
        }
        binding.nextImg.setOnClickListener {
            binding.imagePager.currentItem = binding.imagePager.currentItem+1
        }
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostImageBinding {
        return FragmentPostImageBinding.inflate(inflater, container, false)
    }

}