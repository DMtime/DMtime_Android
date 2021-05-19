package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.GalleryApi
import com.jaemin.features.data.repository.GalleryRepositoryImpl
import com.jaemin.features.domain.repository.GalleryRepository
import com.jaemin.features.domain.usecase.AddGalleryUseCase
import com.jaemin.features.domain.usecase.GetPostsUseCase
import com.jaemin.features.presentation.gallery.contract.GalleryContract
import com.jaemin.features.presentation.gallery.presenter.GalleryPresenter
import com.jaemin.features.presentation.main.contract.AddGalleryContract
import com.jaemin.features.presentation.main.presenter.AddGalleryPresenter

import org.koin.dsl.module
import retrofit2.Retrofit

val galleryModule = module {
    fun provideGalleryApi(retrofit: Retrofit): GalleryApi =
        retrofit.create(GalleryApi::class.java)


    factory { provideGalleryApi(get()) }
    factory<GalleryContract.Presenter> { (view: GalleryContract.View) -> GalleryPresenter(view, get()) }
    factory<GalleryRepository> { GalleryRepositoryImpl(get(),get()) }
    factory<AddGalleryContract.Presenter> { (view: AddGalleryContract.View) -> AddGalleryPresenter(view, get()) }

    factory { GetPostsUseCase(get()) }
    factory { AddGalleryUseCase(get()) }
}