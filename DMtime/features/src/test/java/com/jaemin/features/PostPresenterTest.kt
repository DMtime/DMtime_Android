package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.Post
import com.jaemin.features.domain.entity.Uploader
import com.jaemin.features.domain.repository.PostRepository
import com.jaemin.features.domain.usecase.GetPostUseCase
import com.jaemin.features.domain.usecase.PostDislikeUseCase
import com.jaemin.features.domain.usecase.PostLikeUseCase
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
    private lateinit var postLikeUseCase: PostLikeUseCase
    private lateinit var postDislikeUseCase: PostDislikeUseCase

    private lateinit var postPresenter: PostContract.Presenter
    @Mock
    private lateinit var postRepository: PostRepository

    @Before
    override fun before() {
        super.before()
        getPostUseCase= GetPostUseCase(postRepository)
        postLikeUseCase = PostLikeUseCase(postRepository)
        postDislikeUseCase = PostDislikeUseCase(postRepository)
        postPresenter = PostPresenter(getPostUseCase, postLikeUseCase, postDislikeUseCase, postView)
        `when`(postView.getPostId()).thenReturn(1)
    }

    @Test
    fun `(Given) 상세 게시글 조회 시 (When) 상세 게시글 화면 진입 시(Then) 게시글 값 설정`(){
        val post =Post(
            content = "test",
            isAnonymous = false,
            isMine = false,
            id = 1,
            images = listOf(),
            numberOfLikes = 1,
            numberOfDislikes = 1,
            postedDatetime = "dd",
            postedGallery = Gallery("test","test","test",1),
            title = "test",
            uploader = Uploader("test","test"),
            views = 1,
            myReaction = "none"
        )
        `when`(postRepository.getPost(Mockito.anyInt())).thenReturn(Single.just(post))

        postPresenter.onCreate()

        verify(postView).setPost(post)

    }

    @Test
    fun `(Given) 상세 게시글 조회 실패 (When) 상세 게시글 화면 진입 시 (Then) 오류 화면 표시`(){

        `when`(postRepository.getPost(1)).thenReturn(Single.error(Exception("testException")))
        postPresenter.onCreate()

        verify(postView).showErrorScreen()


    }

//    @Test
//    fun postLikeSuccess(){
//        postPresenter.onClickLikeButton()
////        verify(postView).setPostLike()
////        verify(postView).disablePostLikeButton()
//    }
//
//    @Test
//    fun postDislikeSuccess(){
//        postPresenter.onClickLikeButton()
////        verify(postView).setPostLike()
////        verify(postView).disablePostLikeButton()
//    }
//
//    @Test
//    fun postLikeFail(){
//        postPresenter.onClickLikeButton()
////        verify(postView).setPostLike()
////        verify(postView).disablePostLikeButton()
//    }
//
//    @Test
//    fun postDislikeFail(){
//        postPresenter.onClickLikeButton()
////        verify(postView).setPostLike()
////        verify(postView).disablePostLikeButton()
//    }
}