package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.repository.CommentsRepository
import com.jaemin.features.domain.usecase.GetCommentsUseCase
import com.jaemin.features.domain.usecase.WriteCommentUseCase
import com.jaemin.features.presentation.post.contract.CommentsContract
import com.jaemin.features.presentation.post.presenter.CommentsPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class CommentsPresenterTest : BaseTest(){

    @Mock
    private lateinit var getCommentsUseCase : GetCommentsUseCase


    private lateinit var writeCommentUseCase: WriteCommentUseCase

    private lateinit var commentsPresenter : CommentsPresenter
    @Mock
    private lateinit var commentsView : CommentsContract.View

    @Mock
    private lateinit var commentsRepository : CommentsRepository

    @Before
    override fun before() {
        super.before()
        writeCommentUseCase = WriteCommentUseCase(commentsRepository)
        commentsPresenter = CommentsPresenter(getCommentsUseCase, writeCommentUseCase, commentsView)
    }

    @Test
    fun getCommentsSuccess() {
        val comments = Comments(listOf(), 1)
        `when`(commentsRepository.getComments(Mockito.anyInt())).thenReturn(Single.just(comments))

        commentsPresenter.onCreate(1)

    }

    @Test
    fun writeCommentsSuccess() {
        val comment = CommentInProgress(false, null, "test")
        val postId = 1

        `when`(commentsView.getPostId()).thenReturn(postId)
        `when`(commentsRepository.writeComment(Pair(postId,comment))).thenReturn(Single.just(true))

        commentsPresenter.onClickCommentButton(comment)

        verify(commentsView).showCommentSuccessMessage()
        verify(commentsView).clearCommentContent()
        verify(commentsView).moveToComment()
    }
    @Test
    fun writeReplyCommentsSuccess() {
        val comment = CommentInProgress(false, 1, "test")
        val postId = 1

        `when`(commentsView.getPostId()).thenReturn(postId)
        `when`(commentsRepository.writeComment(Pair(postId,comment))).thenReturn(Single.just(true))

        commentsPresenter.onClickReplyCommentButton(comment)

        verify(commentsView).showCommentSuccessMessage()
        verify(commentsView).clearCommentContent()
    }
    @Test
    fun writeCommentsFailed() {
        val comment = CommentInProgress(false, null, "test")
        val postId = 1

        `when`(commentsView.getPostId()).thenReturn(postId)
        `when`(commentsRepository.writeComment(Pair(postId,comment))).thenReturn(Single.error(Exception("testException")))

        commentsPresenter.onClickCommentButton(comment)

        verify(commentsView).showCommentFailMessage()
    }
}