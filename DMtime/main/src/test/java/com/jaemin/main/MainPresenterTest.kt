package com.jaemin.main

import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.entity.Post
import com.jaemin.main.domain.entity.Uploader
import com.jaemin.main.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.main.presentation.MainContract
import com.jaemin.main.presentation.MainPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private lateinit var mainView: MainContract.View

    @Mock
    private lateinit var getDefaultGalleriesUseCase: GetDefaultGalleriesUseCase

    private lateinit var mainPresenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainPresenter = MainPresenter(mainView, getDefaultGalleriesUseCase)
    }

    @Test
    fun getDefaultGalleries() {

        `when`(getDefaultGalleriesUseCase.execute()).thenReturn(Single.just(listOf(
            DefaultGallery(1,"d",
                listOf())
        )))
       mainPresenter.onCreate()
       verify(mainView).setDefaultGalleries(listOf(DefaultGallery(1,"d", listOf())))
    }
}