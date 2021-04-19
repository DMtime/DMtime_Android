package com.jaemin.features


import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.domain.repository.PostRepository
import com.jaemin.features.domain.usecase.WritePostsUseCase
import com.jaemin.features.presentation.post.contract.WritePostContract
import com.jaemin.features.presentation.post.presenter.WritePostPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class WritePostPresenterTest : BaseTest(){
    @Mock
    private lateinit var writePostView: WritePostContract.View

    private lateinit var writePostUseCase: WritePostsUseCase

    private lateinit var writePostPresenter: WritePostContract.Presenter

    @Mock
    private lateinit var postRepository: PostRepository

    @Before
    override fun before() {
        super.before()
        writePostUseCase = WritePostsUseCase(postRepository)
        writePostPresenter = WritePostPresenter(writePostView, writePostUseCase)
    }

    @Test
    fun writePostSuccess(){
        val writtenPost = WrittenPost(false, listOf(),"d","d")
        `when`(postRepository.writePost(writtenPost)).thenReturn(Single.just(true))


        writePostPresenter.onClickWritePostButton(writtenPost)


        verify(writePostView).showSuccessMessage()

    }
}