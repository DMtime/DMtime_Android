package com.jaemin.dmtime.di

import com.jaemin.gallery.data.remote.GalleryApi
import com.jaemin.gallery.data.remote.PostsApi
import com.jaemin.gallery.data.repository.GalleryRepositoryImpl
import com.jaemin.gallery.domain.repository.GalleryRepository
import com.jaemin.gallery.domain.usecase.GetPostsUseCase
import com.jaemin.gallery.presentation.contract.GalleryContract
import com.jaemin.gallery.presentation.presenter.GalleryPresenter

import org.koin.dsl.module
import retrofit2.Retrofit

val galleryModule = module {
    fun provideGalleryApi(retrofit: Retrofit): GalleryApi =
        retrofit.create(GalleryApi::class.java)


    factory { provideGalleryApi(get()) }
    factory<GalleryContract.Presenter> { (view: GalleryContract.View) -> GalleryPresenter(view, get()) }
    factory<GalleryRepository> { GalleryRepositoryImpl(get()) }
    factory { GetPostsUseCase(get()) }
}