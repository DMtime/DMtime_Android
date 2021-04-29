package com.jaemin.features.presentation.post.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentPostBinding
import com.jaemin.features.domain.entity.Comment
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.presentation.post.contract.CommentsContract
import com.jaemin.features.presentation.post.contract.PostContract
import com.jaemin.features.presentation.post.adapter.PostCommentAdapter
import com.jaemin.features.presentation.post.adapter.PostImageAdapter
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class PostFragment : BaseFragment<FragmentPostBinding>(), PostContract.View, CommentsContract.View {
    private val postPresenter: PostContract.Presenter by inject { parametersOf(this) }
    private val commentsPresenter: CommentsContract.Presenter by inject { parametersOf(this) }
    private lateinit var postImageAdapter: PostImageAdapter
    private lateinit var postCommentAdapter: PostCommentAdapter

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPostBinding {
        return FragmentPostBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postCommentAdapter= PostCommentAdapter(commentsPresenter)
        binding.commentsRv.adapter = postCommentAdapter
        postImageAdapter = PostImageAdapter(postPresenter = postPresenter)
        binding.postImageRv.adapter = postImageAdapter
        postPresenter.onCreate()
        commentsPresenter.onCreate(getPostId())
        binding.postDetailBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.postCommentConfirmTv.setOnClickListener {
            commentsPresenter.onClickCommentButton(getCommentInProgress())
        }
        binding.postLikeLayout.setOnClickListener {
            postPresenter.onClickLikeButton()
        }
        binding.postDislikeLayout.setOnClickListener {
            postPresenter.onClickDislikeButton()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                requireActivity().overridePendingTransition(0, R.anim.slide_out_left)
//
//            }
//        })
    }
    override fun setPost(post: Post) {
        binding.postTitleTv.text = post.title
        binding.postContentTv.text = post.content
        binding.postDateDetailTv.text = post.postedDatetime
        binding.postWriterDetailTv.text = post.uploader.username
        setPostLike(post.numberOfLikes)
        setPostDislike(post.numberOfDislikes)
        binding.postRecommendationDetailTv.text = post.numberOfLikes.toString()
        postImageAdapter.updateItems(post.images)
    }

    override fun showImage(position: Int) {
        val args = Bundle().apply {
            putStringArrayList("images", postImageAdapter.getImages())
            putInt("position", position)
        }


        PostImageFragment().apply {
            arguments= args
        }.show(requireActivity().supportFragmentManager, "PostImageFragment")
    }

    override fun showErrorScreen() {
    }

    override fun setPostLike(likes: Int) {
        binding.postLikeCountTv.text = likes.toString()
    }

    override fun setPostDislike(dislikes: Int) {
        binding.postDislikeCountTv.text = dislikes.toString()
    }

    override fun enablePostLikeButton() {
        binding.postLikeImg.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_like))
    }

    override fun enablePostDislikeButton() {
        binding.postDislikeImg.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_dislike))
    }

    override fun disablePostLikeButton() {
        binding.postLikeImg.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_like_filled))
    }

    override fun disablePostDislikeButton() {
        binding.postDislikeImg.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_dislike_filled))
    }

    override fun setComments(comments: List<Comment>) {
        (binding.commentsRv.adapter as PostCommentAdapter).updateItems(comments)
    }

    override fun getCommentInProgress(): CommentInProgress =
        CommentInProgress(binding.commentAnonymousCheckbox.isChecked,null,binding.postCommentEt.text.toString())

    override fun moveToComment() {
        binding.commentsRv.scrollToPosition(postCommentAdapter.itemCount-1)
    }

    override fun getPostId(): Int =
        requireArguments().getInt("postId")

    override fun showCommentFailMessage() {
        Toasty.error(requireActivity(),"댓글 등록을 실패했습니다.", Toast.LENGTH_SHORT, true).show()

    }

    override fun showCommentSuccessMessage() {
        Toasty.success(requireActivity(),"댓글을 등록 하셨습니다.", Toast.LENGTH_SHORT, true).show()
    }

    override fun clearCommentContent() {
        binding.postCommentEt.text.clear()
    }

    override fun onStart() {
        super.onStart()
        Timber.d("dtonStart: ${getPostId()}")
    }
    override fun onResume() {
        super.onResume()
        Timber.d("dtonResume: ${getPostId()}")
    }
    override fun onPause() {
        super.onPause()
        Timber.d("dtonPause : ${getPostId()}")
    }
    override fun onStop() {
        super.onStop()
        Timber.d("dtonStop : ${getPostId()}")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("dtonDestroyView : ${getPostId()}")
    }
    override fun onDetach() {
        super.onDetach()
        Timber.d("dtonDetach : ${getPostId()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("dtonDestroy : ${getPostId()}")
    }

}