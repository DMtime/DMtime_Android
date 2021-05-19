package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.entity.CommentInProgress
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.repository.CommentsRepository
import com.jaemin.features.domain.usecase.DeleteCommentUseCase
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

    @Mock
    private lateinit var deleteCommentUseCase: DeleteCommentUseCase

    private lateinit var commentsPresenter : CommentsPresenter
    @Mock
    private lateinit var commentsView : CommentsContract.View

    @Mock
    private lateinit var commentsRepository : CommentsRepository

    @Before
    override fun before() {
        super.before()
        writeCommentUseCase = WriteCommentUseCase(commentsRepository)
        commentsPresenter = CommentsPresenter(getCommentsUseCase, writeCommentUseCase, deleteCommentUseCase,commentsView)
    }

    @Test
    fun `(Given) 댓글 리스트 조회 시 (When) 상세 게시글 화면 진입 시 (Then) 댓글 표시`() {
        val comments = Comments(listOf(), 1)
        `when`(commentsRepository.getComments(Mockito.anyInt())).thenReturn(Single.just(comments))

        commentsPresenter.onCreate(1)

    }

    @Test
    fun `(Given) 댓글 (When) 댓글 작성 시 (Then) 작성 성공 메시지 표시 후, 댓글 창 초기화 후, 댓글 표시`() {
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
    fun `(Given) 대댓글 (When) 대댓글 작성 시 (Then) 작성 성공 메시지 표시 후, 대댓글 창 초기화 후, 대댓글 표시`() {
        val comment = CommentInProgress(false, 1, "test")
        val postId = 1

        `when`(commentsView.getPostId()).thenReturn(postId)
        `when`(commentsRepository.writeComment(Pair(postId,comment))).thenReturn(Single.just(true))

        commentsPresenter.onClickReplyCommentButton(comment)

        verify(commentsView).showCommentSuccessMessage()
        verify(commentsView).clearCommentContent()
    }
    @Test
    fun `(Given) 댓글 (When) 댓글 작성 실패 시 (Then) 댓글 작성 실패 메시지 표시`() {
        val comment = CommentInProgress(false, null, "test")
        val postId = 1

        `when`(commentsView.getPostId()).thenReturn(postId)
        `when`(commentsRepository.writeComment(Pair(postId,comment))).thenReturn(Single.error(Exception("testException")))

        commentsPresenter.onClickCommentButton(comment)

        verify(commentsView).showCommentFailMessage()
    }
}