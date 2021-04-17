package com.jaemin.dmtime.di

import com.jaemin.gallery.data.remote.CommentsApi
import com.jaemin.gallery.data.repository.CommentsRepositoryImpl
import com.jaemin.gallery.domain.repository.CommentsRepository
import com.jaemin.gallery.domain.usecase.GetCommentsUseCase
import com.jaemin.gallery.presentation.contract.CommentsContract
import com.jaemin.gallery.presentation.presenter.CommentsPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val commentsModule = module {
    fun provideCommentsApi(retrofit: Retrofit): CommentsApi =
        retrofit.create(CommentsApi::class.java)

    factory { provideCommentsApi(get()) }
    factory<CommentsContract.Presenter> { (view: CommentsContract.View) -> CommentsPresenter(get(),view) }
    factory<CommentsRepository> { CommentsRepositoryImpl(get()) }
    factory { GetCommentsUseCase(get()) }
}