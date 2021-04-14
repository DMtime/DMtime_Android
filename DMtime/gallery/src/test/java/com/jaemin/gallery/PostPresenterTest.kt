package com.jaemin.gallery

import com.jaemin.gallery.base.BaseTest
import com.jaemin.gallery.domain.entity.Post
import com.jaemin.gallery.domain.entity.PostedGallery
import com.jaemin.gallery.domain.entity.Uploader
import com.jaemin.gallery.domain.repository.PostRepository
import com.jaemin.gallery.domain.usecase.GetPostUseCase
import com.jaemin.gallery.presentation.contract.PostContract
import com.jaemin.gallery.presentation.presenter.PostPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class PostPresenterTest : BaseTest(){
    @Mock
    private lateinit var postView: PostContract.View

    private lateinit var getPostUseCase: GetPostUseCase

    private lateinit var postPresenter: PostContract.Presenter
    @Mock
    private lateinit var postRepository: PostRepository

    @Before
    override fun before() {
        super.before()
        getPostUseCase= GetPostUseCase(postRepository)
        postPresenter = PostPresenter(getPostUseCase, postView)
    }

    @Test
    fun getPostSuccess(){

        `when`(postView.getPostId()).thenReturn(1)

        val post =Post("test",false,postView.getPostId(), listOf(),1,
            1,"dd",PostedGallery("free","test"),"test",
            Uploader("test"),1)
        `when`(postRepository.getPost(postView.getPostId())).thenReturn(Single.just(post))

        postPresenter.onCreate()

        verify(postView).setPost(post)

    }

    @Test
    fun getPostFailed(){

        `when`(postView.getPostId()).thenReturn(1)

        `when`(postRepository.getPost(postView.getPostId())).thenReturn(Single.error(Exception("testException")))

        postPresenter.onCreate()

        verify(postView).showErrorScreen()


    }
}