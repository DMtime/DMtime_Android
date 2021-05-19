package com.jaemin.features.presentation.post.contract

import com.jaemin.base.BasePresenter
import com.jaemin.features.domain.entity.Comment
import com.jaemin.features.domain.entity.CommentInProgress

interface CommentsContract {
    interface View {
        fun setComments(comments: List<Comment>)

        fun getCommentInProgress() : CommentInProgress

        fun moveToComment()

        fun getPostId() : Int

        fun showCommentFailMessage()

        fun showCommentSuccessMessage()

        fun clearCommentContent()



    }

    interface Presenter : BasePresenter {
        fun onCreate(postId: Int)

        fun onClickCommentButton(comment : CommentInProgress)

        fun onClickCommentDeleteButton(commentId : Int)

        fun onClickReplyCommentButton(replyComment : CommentInProgress)

        fun onClickCommentReportButton()

    }
}