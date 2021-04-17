package com.jaemin.dmtime.di

import com.jaemin.gallery.data.remote.PostApi
import com.jaemin.gallery.data.remote.PostsApi
import com.jaemin.gallery.data.repository.PostRepositoryImpl
import com.jaemin.gallery.domain.repository.PostRepository
import com.jaemin.gallery.domain.usecase.GetPostUseCase
import com.jaemin.gallery.presentation.contract.PostContract
import com.jaemin.gallery.presentation.presenter.PostPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val postModule = module {
    fun providePostApi(retrofit: Retrofit): PostApi =
        retrofit.create(PostApi::class.java)

    fun providePostsApi(retrofit: Retrofit): PostsApi =
        retrofit.create(PostsApi::class.java)

    factory { providePostApi(get()) }
    factory { providePostsApi(get()) }

    factory<PostContract.Presenter> { (view: PostContract.View) -> PostPresenter(get(), view) }
    factory<PostRepository> { PostRepositoryImpl(get()) }
    factory { GetPostUseCase(get()) }
}