package com.jaemin.gallery.presentation.contract

import com.jaemin.gallery.domain.entity.Comment
import com.jaemin.gallery.domain.entity.Comments

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