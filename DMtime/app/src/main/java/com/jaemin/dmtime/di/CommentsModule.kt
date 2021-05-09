package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.CommentsApi
import com.jaemin.features.data.repository.CommentsRepositoryImpl
import com.jaemin.features.domain.repository.CommentsRepository
import com.jaemin.features.domain.usecase.GetCommentsUseCase
import com.jaemin.features.domain.usecase.WriteCommentUseCase
import com.jaemin.features.presentation.post.contract.CommentsContract
import com.jaemin.features.presentation.post.presenter.CommentsPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val commentsModule = module {
    fun provideCommentsApi(retrofit: Retrofit): CommentsApi =
        retrofit.create(CommentsApi::class.java)

    factory { provideCommentsApi(get()) }
    factory<CommentsContract.Presenter> { (view: CommentsContract.View) -> CommentsPresenter(get(),get(),view) }
    factory<CommentsRepository> { CommentsRepositoryImpl(get()) }
    factory { GetCommentsUseCase(get()) }
    factory { WriteCommentUseCase(get()) }

}