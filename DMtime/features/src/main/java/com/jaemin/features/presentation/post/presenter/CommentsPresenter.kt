package com.jaemin.features.presentation.post.presenter

import com.jaemin.features.domain.entity.Comment
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.usecase.DeleteCommentUseCase
import com.jaemin.features.domain.usecase.GetCommentsUseCase
import com.jaemin.features.domain.usecase.WriteCommentUseCase
import com.jaemin.features.presentation.post.contract.CommentsContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver

class CommentsPresenter(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val writeCommentUseCase: WriteCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val commentsView: CommentsContract.View
) : CommentsContract.Presenter {
    private val postId by lazy {
        commentsView.getPostId()
    }
    override fun onCreate(postId: Int) {
        getComments(postId)
    }
    private fun getComments(postId: Int){
        getCommentsUseCase.execute(postId, object : DisposableSingleObserver<Comments>(){
            override fun onSuccess(comments: Comments) {
                commentsView.setComments(commentSort(comments.comments))
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        })
    }
    private fun commentSort(comments: List<Comment>) : List<Comment>{
        val upperComments = comments.reversed().toMutableList()
        val replyComments = upperComments.filter { it.upperCommentId!=null }.toMutableList()
        for (i in replyComments.indices) {
            for (j in 1..upperComments.size) {
                if (replyComments[i].upperCommentId == upperComments[j-1].id) {
                    upperComments.remove(replyComments[i])
                    upperComments.add(j-1, replyComments[i])
                }
            }
        }
        return upperComments
    }

    override fun onClickCommentButton(comment : CommentInProgress) {
        writeCommentUseCase.execute(Pair(postId,comment),object : DisposableSingleObserver<Boolean>(){
            override fun onSuccess(t: Boolean) {
                commentsView.showCommentSuccessMessage()
                commentsView.clearCommentContent()
                getComments(postId)
                commentsView.moveToComment()
            }
            override fun onError(e: Throwable) {
                commentsView.showCommentFailMessage()
            }
        })
    }

    override fun onClickCommentDeleteButton(commentId: Int) {
        deleteCommentUseCase.execute(commentId,object : DisposableSingleObserver<Unit>(){
            override fun onSuccess(t: Unit) {
                getComments(postId)
            }

            override fun onError(e: Throwable?) {

            }

        })
    }

    override fun onClickReplyCommentButton(replyComment: CommentInProgress) {
        writeCommentUseCase.execute(Pair(postId,replyComment),object : DisposableSingleObserver<Boolean>(){
            override fun onSuccess(t: Boolean) {
                commentsView.showCommentSuccessMessage()
                commentsView.clearCommentContent()
                getComments(postId)
            }
            override fun onError(e: Throwable) {
                commentsView.showCommentFailMessage()
            }
        })
    }


    override fun onClickCommentReportButton() {
    }

    override fun onDestroy() {
        deleteCommentUseCase.dispose()
        getCommentsUseCase.dispose()
        writeCommentUseCase.dispose()
    }


}