package com.jaemin.gallery.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.gallery.databinding.FragmentPostBinding
import com.jaemin.gallery.domain.entity.Comment
import com.jaemin.gallery.domain.entity.Comments
import com.jaemin.gallery.domain.entity.Post
import com.jaemin.gallery.presentation.contract.CommentsContract
import com.jaemin.gallery.presentation.contract.PostContract
import com.jaemin.gallery.presentation.view.adapter.PostCommentAdapter
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class PostFragment : BaseFragment<FragmentPostBinding>(), PostContract.View, CommentsContract.View {
    private val postPresenter: PostContract.Presenter by inject { parametersOf(this) }
    private val commentsPresenter: CommentsContract.Presenter by inject { parametersOf(this) }

    private val postId : Int by lazy {
        requireArguments().getInt("postId")
    }
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPostBinding {
        return FragmentPostBinding.inflate(inflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.commentsRv.adapter = PostCommentAdapter(commentsPresenter)
        postPresenter.onCreate(postId)
        commentsPresenter.onCreate(postId)
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

    override fun showErrorScreen() {
    }

    override fun setComments(comments: List<Comment>) {
        (binding.commentsRv.adapter as PostCommentAdapter).updateItems(comments)
    }

}