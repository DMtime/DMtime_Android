package com.jaemin.main

import com.jaemin.main.base.BaseTest
import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.repository.MainRepository
import com.jaemin.main.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.main.presentation.MainContract
import com.jaemin.main.presentation.MainPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class MainPresenterTest : BaseTest(){

    @Mock
    private lateinit var mainView: MainContract.View

    private lateinit var getDefaultGalleriesUseCase: GetDefaultGalleriesUseCase

    @Mock
    private lateinit var mainRepository: MainRepository

    private lateinit var mainPresenter: MainContract.Presenter

    @Before
    override fun before(){
        super.before()
        getDefaultGalleriesUseCase = GetDefaultGalleriesUseCase(mainRepository)
        mainPresenter = MainPresenter(mainView, getDefaultGalleriesUseCase)
    }

    @Test
    fun setDefaultGalleriesSuccess() {
        val defaultGalleries = listOf(DefaultGallery(1, "d", listOf()))
        `when`(mainRepository.getDefaultGalleries()).thenReturn(Single.just(defaultGalleries))

        mainPresenter.onCreate()

        verify(mainView).setDefaultGalleries(listOf(DefaultGallery(1, "d", listOf())))
    }

    @Test
    fun setDefaultGalleriesFailed() {
        `when`(mainRepository.getDefaultGalleries()).thenReturn(Single.error(Exception("testException")))

        mainPresenter.onCreate()

        verify(mainView).setDefaultGalleriesFailed()
    }

    @Test
    fun onClickPostSuccess() {
        mainPresenter.onClickPost(1)

        verify(mainView).moveToPost(1)
    }
}