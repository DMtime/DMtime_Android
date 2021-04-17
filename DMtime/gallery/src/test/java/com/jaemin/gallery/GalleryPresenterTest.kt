package com.jaemin.gallery

import com.jaemin.gallery.base.BaseTest
import com.jaemin.gallery.domain.entity.Gallery
import com.jaemin.gallery.domain.entity.PostPreview
import com.jaemin.gallery.domain.entity.Posts
import com.jaemin.gallery.domain.entity.Uploader
import com.jaemin.gallery.domain.repository.GalleryRepository
import com.jaemin.gallery.domain.usecase.GetPostsUseCase
import com.jaemin.gallery.presentation.contract.GalleryContract
import com.jaemin.gallery.presentation.presenter.GalleryPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class GalleryPresenterTest : BaseTest() {
    @Mock
    private lateinit var galleryView: GalleryContract.View

    private lateinit var getPostsUseCase: GetPostsUseCase

    private lateinit var galleryPresenter: GalleryContract.Presenter

    @Mock
    private lateinit var galleryRepository: GalleryRepository

    @Before
    override fun before() {
        super.before()
        getPostsUseCase = GetPostsUseCase(galleryRepository)
        galleryPresenter = GalleryPresenter(galleryView, getPostsUseCase)
    }

    @Test
    fun getPostsSuccess() {
        val posts = Posts(
            1, listOf(
                PostPreview(
                    1, "none", 2, Uploader("dd", "test"), "test",
                    1, "test", false, false, 1, Gallery("test", "test", "test", 1), 1
                )
            )
        )

        `when`(galleryRepository.getPosts(4, 1, "free")).thenReturn(Single.just(posts))

        `when`(galleryView.getGalleryId()).thenReturn("free")

        galleryPresenter.onCreate()
        verify(galleryView).setPosts(posts.posts)
    }

    @Test
    fun getPostsFail() {

        `when`(
            galleryRepository.getPosts(
                4,
                1,
                "free"
            )
        ).thenReturn(Single.error(Exception("testException")))

        `when`(galleryView.getGalleryId()).thenReturn("free")

        galleryPresenter.onCreate()
        verify(galleryView).showGetPostsFailedMessage()

    }
}