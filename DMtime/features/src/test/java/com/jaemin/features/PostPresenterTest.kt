package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.Uploader
import com.jaemin.features.domain.repository.PostRepository
import com.jaemin.features.domain.usecase.GetPostUseCase
import com.jaemin.features.presentation.post.contract.PostContract
import com.jaemin.features.presentation.post.presenter.PostPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
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


        val post =Post("test",false,1, listOf(),1,
            1,"dd", Gallery("test","test","test",1),"test",
            Uploader("test","test"),1)
        `when`(postRepository.getPost(Mockito.anyInt())).thenReturn(Single.just(post))

        postPresenter.onCreate(Mockito.anyInt())

        verify(postView).setPost(post)

    }

    @Test
    fun getPostFailed(){

        `when`(postRepository.getPost(1)).thenReturn(Single.error(Exception("testException")))

        postPresenter.onCreate(1)

        verify(postView).showErrorScreen()


    }
}