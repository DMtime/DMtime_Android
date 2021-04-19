package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.entity.Comments
import com.jaemin.features.domain.repository.CommentsRepository
import com.jaemin.features.domain.usecase.GetCommentsUseCase
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

    private lateinit var getCommentsUseCase : GetCommentsUseCase

    private lateinit var commentsPresenter : CommentsContract.Presenter
    @Mock
    private lateinit var commentsView : CommentsContract.View

    @Mock
    private lateinit var commentsRepository : CommentsRepository

    @Before
    override fun before() {
        super.before()
        getCommentsUseCase = GetCommentsUseCase(commentsRepository)
        commentsPresenter = CommentsPresenter(getCommentsUseCase, commentsView)
    }

    @Test
    fun getCommentsSuccess() {
        val comments = Comments(listOf(), 1)
        `when`(commentsRepository.getComments(Mockito.anyInt())).thenReturn(Single.just(comments))

        commentsPresenter.onCreate(Mockito.anyInt())
        verify(commentsView).setComments(comments.comments)



    }
}