package com.jaemin.features.presentation.post.presenter

import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.usecase.GetCommentsUseCase
import com.jaemin.features.presentation.post.contract.CommentsContract
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