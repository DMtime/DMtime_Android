package com.jaemin.features.presentation.post.contract

import com.jaemin.features.domain.entity.Comment

interface CommentsContract {
    interface View {
        fun setComments(comments: List<Comment>)

    }

    interface Presenter {
        fun onCreate(postId: Int)

        fun onClickCommentButton()

        fun onClickReplyCommentButton()

        fun onClickCommentReportButton()

    }
}