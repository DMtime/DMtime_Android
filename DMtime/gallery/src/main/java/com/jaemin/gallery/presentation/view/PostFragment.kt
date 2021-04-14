package com.jaemin.gallery.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.gallery.databinding.FragmentPostBinding
import com.jaemin.gallery.domain.entity.Post
import com.jaemin.gallery.presentation.contract.PostContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class PostFragment : BaseFragment<FragmentPostBinding>(), PostContract.View {
    private val presenter: PostContract.Presenter by inject { parametersOf(this) }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPostBinding {
        return FragmentPostBinding.inflate(inflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate()
        binding.postDetailBackBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this)
                .commit()
        }
    }

    override fun setPost(post: Post) {
        binding.postTitleTv.text = post.title
        binding.postContentTv.text = post.content
        binding.postDateDetailTv.text = post.postedDatetime
        binding.postWriterDetailTv.text = post.uploader.username
        binding.postLikeCountTv.text = post.numberOfLikes.toString()
        binding.postDislikeCountTv.text = post.numberOfDislikes.toString()
        binding.postRecommendationDetailTv.text = post.numberOfLikes.toString()

    }

    override fun getPostId(): Int {
        return requireArguments().getInt("postId")
    }

    override fun showErrorScreen() {
    }

}