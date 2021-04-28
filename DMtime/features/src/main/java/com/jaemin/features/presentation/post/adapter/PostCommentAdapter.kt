package com.jaemin.features.presentation.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> CommentViewHolder(
                ItemCommentBinding.inflate(inflater, parent, false),
                commentsPresenter
            )
            else -> ReplyCommentViewHolder(
                ItemReplyCommentBinding.inflate(inflater, parent, false),
                commentsPresenter
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
        private val commentsPresenter: CommentsContract.Presenter
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.postReplyCommentLayout.visibility = View.GONE


            binding.commentWriterTv.text = comment.writer.username
            binding.commentContentTv.text = comment.content
            binding.commentTimeTv.text = comment.wroteDatetime

            binding.commentAddTv.setOnClickListener {
                if (binding.postReplyCommentLayout.visibility == View.GONE) {
                    binding.postReplyCommentLayout.visibility = View.VISIBLE
                }
                else {
                    binding.postReplyCommentEt.text.clear()
                    binding.postReplyCommentLayout.visibility = View.GONE
                }
            }
            binding.postReplyCommentConfirmTv.setOnClickListener {
                commentsPresenter.onClickReplyCommentButton(
                    CommentInProgress(
                        binding.commentAnonymousCheckbox.isChecked,
                        comment.id,
                        binding.postReplyCommentEt.text.toString()
                    )
                )
            }
        }


    }

    inner class ReplyCommentViewHolder(
        private val binding: ItemReplyCommentBinding,
        private val commentsPresenter: CommentsContract.Presenter
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.postReplyCommentLayout.visibility = View.GONE

            binding.commentWriterTv.text = comment.writer.username
            binding.commentContentTv.text = comment.content
            binding.commentTimeTv.text = comment.wroteDatetime

            binding.commentAddTv.setOnClickListener {
                if (binding.postReplyCommentLayout.visibility == View.GONE) {
                    binding.postReplyCommentLayout.visibility = View.VISIBLE
                }
                else {
                    binding.postReplyCommentEt.text.clear()
                    binding.postReplyCommentLayout.visibility = View.GONE
                }
            }
            binding.postReplyCommentConfirmTv.setOnClickListener {
                commentsPresenter.onClickReplyCommentButton(
                    CommentInProgress(
                        binding.commentAnonymousCheckbox.isChecked,
                        comment.id,
                        binding.postReplyCommentEt.text.toString()
                    )
                )
            }
        }

    }

}

