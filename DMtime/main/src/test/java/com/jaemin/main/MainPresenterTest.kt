package com.jaemin.main

import com.jaemin.main.base.BaseTest
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.data.repository.MainRepository
import com.jaemin.features.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.features.presentation.view.main.MainContract
import com.jaemin.features.presentation.view.main.MainPresenter
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
        val defaultGalleries = listOf(DefaultGallery("free", "d", listOf()))
        `when`(mainRepository.getDefaultGalleries()).thenReturn(Single.just(defaultGalleries))

        mainPresenter.onCreate()

        verify(mainView).setDefaultGalleries(listOf(DefaultGallery("free", "d", listOf())))
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

    @Test
    fun onClickGallerySuccess() {
        mainPresenter.onClickGallery("free")
        verify(mainView).moveToGallery("free")
    }
}