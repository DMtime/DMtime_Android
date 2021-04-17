package com.jaemin.gallery.presentation.presenter

import android.util.Log
import com.jaemin.gallery.domain.entity.Comment
import com.jaemin.gallery.domain.entity.Comments
import com.jaemin.gallery.domain.usecase.GetCommentsUseCase
import com.jaemin.gallery.presentation.contract.CommentsContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class CommentsPresenter(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val commentsView: CommentsContract.View
) : CommentsContract.Presenter {
    override fun onCreate(postId: Int) {
        getCommentsUseCase.execute(postId, object : DisposableSingleObserver<Comments>(){
            override fun onSuccess(comments: Comments) {
                commentsView.setComments(comments.comments)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })

    }
//    fun commentSort(comments: List<Comment>) : List<Comment>{
//        val sortedComments = comments.toMutableList()
//        val upperCommentList = mutableMapOf<Int,Comment>()
////        val upperCommentList = mutableSetOf<Int>()
//
//        sortedComments.reversed().forEach {
//
//            if(it.upperCommentId!=null) {
//                sortedComments.remove(it)
//                upperCommentList.pu(it.upperCommentId)
//            }
//        }
//
//    }

    override fun onClickCommentButton() {
    }

    override fun onClickReplyCommentButton() {
    }

    override fun onClickCommentReportButton() {
    }


}