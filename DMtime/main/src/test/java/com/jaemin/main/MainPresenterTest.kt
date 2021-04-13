package com.jaemin.main

import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.repository.MainRepository
import com.jaemin.main.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.main.presentation.MainContract
import com.jaemin.main.presentation.MainPresenter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest : BaseTest(){

    @Mock
    private lateinit var mainView: MainContract.View

    private lateinit var getDefaultGalleriesUseCase: GetDefaultGalleriesUseCase

    @Mock
    private lateinit var mainRepository: MainRepository

    private lateinit var mainPresenter: MainContract.Presenter


    @Test
    fun setDefaultGalleriesSuccess() {
        getDefaultGalleriesUseCase = GetDefaultGalleriesUseCase(mainRepository)
        mainPresenter = MainPresenter(mainView, getDefaultGalleriesUseCase)
        val defaultGalleries = listOf(DefaultGallery(1, "d", listOf()))
        `when`(mainRepository.getDefaultGalleries()).thenReturn(Single.just(defaultGalleries))

        mainPresenter.onCreate()

        verify(mainView).setDefaultGalleries(listOf(DefaultGallery(1, "d", listOf())))
    }

    @Test
    fun setDefaultGalleriesFailed() {
        getDefaultGalleriesUseCase = GetDefaultGalleriesUseCase(mainRepository)
        mainPresenter = MainPresenter(mainView, getDefaultGalleriesUseCase)
        `when`(mainRepository.getDefaultGalleries()).thenReturn(Single.error(Exception("testException")))

        mainPresenter.onCreate()

        verify(mainView).setDefaultGalleriesFailed()
    }
}