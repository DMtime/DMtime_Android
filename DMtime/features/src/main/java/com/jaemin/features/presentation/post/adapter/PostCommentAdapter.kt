package com.jaemin.features.presentation.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemCommentBinding
import com.jaemin.features.databinding.ItemReplyCommentBinding
import com.jaemin.features.domain.entity.Comment
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.presentation.post.contract.CommentsContract

class PostCommentAdapter(
    private val commentsPresenter: CommentsContract.Presenter,
    private val comments: MutableList<Comment> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val onClickReplyCommentConfirm =
        { replyComment: CommentInProgress, editText: EditText ->
            commentsPresenter.onClickReplyCommentButton(replyComment)
            editText.text.clear()
        }
    private val onClickDeleteComment =
        { commentId : Int ->
            commentsPresenter.onClickCommentDeleteButton(commentId)
        }
    private val onClickReplyComment = { view: View, editText: EditText ->
        if (view.visibility == View.GONE) {
            view.visibility = View.VISIBLE
        } else {
            editText.text.clear()
            view.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> {
                CommentViewHolder(
                    ItemCommentBinding.inflate(inflater, parent, false),
                    onClickReplyCommentConfirm,
                    onClickDeleteComment,
                    onClickReplyComment
                )
            }
            else -> ReplyCommentViewHolder(
                ItemReplyCommentBinding.inflate(inflater, parent, false),
                onClickReplyCommentConfirm,
                onClickDeleteComment,
                onClickReplyComment
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CommentViewHolder -> holder.bind(comments[position])
            is ReplyCommentViewHolder -> holder.bind(comments[position])
            else -> return
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (comments[position].upperCommentId == null) {
            0
        } else {
            1
        }
    }

    fun updateItems(items: List<Comment>) {
        comments.clear()
        comments.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()

    }

    fun addItem(item: Comment) {
        comments.add(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = comments.size

    inner class CommentViewHolder(
        private val binding: ItemCommentBinding,
        private val onClickReplyCommentConfirm: (CommentInProgress, EditText) -> Unit,
        private val onClickDeleteComment: (Int) -> Unit,
        private val onClickReplyComment: (View, EditText) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.postReplyCommentLayout.visibility = View.GONE
            if (comment.isMine){
                binding.commentDeleteTv.visibility = View.VISIBLE
            }
            binding.commentDeleteTv.setOnClickListener {
                onClickDeleteComment.invoke(comment.id)
            }
            binding.commentWriterTv.text = comment.writer.username
            binding.commentContentTv.text = comment.content
            binding.commentTimeTv.text = comment.wroteDatetime

            binding.commentAddTv.setOnClickListener {
                onClickReplyComment.invoke(
                    binding.postReplyCommentLayout,
                    binding.postReplyCommentEt
                )
            }
            binding.postReplyCommentConfirmTv.setOnClickListener {
                onClickReplyCommentConfirm.invoke(
                    CommentInProgress(
                        binding.commentAnonymousCheckbox.isChecked,
                        comment.id,
                        binding.postReplyCommentEt.text.toString()
                    ),
                    binding.postReplyCommentEt
                )
            }
        }


    }

    inner class ReplyCommentViewHolder(
        private val binding: ItemReplyCommentBinding,
        private val onClickReplyCommentConfirm: (CommentInProgress, EditText) -> Unit,
        private val onClickDeleteComment: (Int) -> Unit,
        private val onClickReplyComment: (View, EditText) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.postReplyCommentLayout.visibility = View.GONE
            if (comment.isMine){
                binding.commentDeleteTv.visibility = View.VISIBLE
            }
            binding.commentDeleteTv.setOnClickListener {
                onClickDeleteComment.invoke(comment.id)
            }

            binding.commentWriterTv.text = comment.writer.username
            binding.commentContentTv.text = comment.content
            binding.commentTimeTv.text = comment.wroteDatetime

            binding.commentAddTv.setOnClickListener {
                onClickReplyComment.invoke(
                    binding.postReplyCommentLayout,
                    binding.postReplyCommentEt
                )
            }
            binding.postReplyCommentConfirmTv.setOnClickListener {
                onClickReplyCommentConfirm.invoke(
                    CommentInProgress(
                        binding.commentAnonymousCheckbox.isChecked,
                        comment.id,
                        binding.postReplyCommentEt.text.toString()
                    ),
                    binding.postReplyCommentEt
                )

            }
        }

    }

}

