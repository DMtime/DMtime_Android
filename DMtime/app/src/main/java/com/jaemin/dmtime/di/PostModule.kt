package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.PostApi
import com.jaemin.features.data.remote.PostsApi
import com.jaemin.features.data.repository.PostRepositoryImpl
import com.jaemin.features.domain.repository.PostRepository
import com.jaemin.features.domain.usecase.GetPostUseCase
import com.jaemin.features.domain.usecase.PostDislikeUseCase
import com.jaemin.features.domain.usecase.PostLikeUseCase
import com.jaemin.features.domain.usecase.WritePostsUseCase
import com.jaemin.features.presentation.post.contract.PostContract
import com.jaemin.features.presentation.post.contract.WritePostContract
import com.jaemin.features.presentation.post.presenter.PostPresenter
import com.jaemin.features.presentation.post.presenter.WritePostPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val postModule = module {
    fun providePostApi(retrofit: Retrofit): PostApi =
        retrofit.create(PostApi::class.java)

    factory { providePostApi(get()) }

    factory<PostContract.Presenter> { (view: PostContract.View) -> PostPresenter(get(), get(),get(),view) }
    factory<PostRepository> { PostRepositoryImpl(get(),get()) }

    factory<WritePostContract.Presenter> { (view: WritePostContract.View) -> WritePostPresenter(view,get()) }

    factory { GetPostUseCase(get()) }
    factory { WritePostsUseCase(get()) }
    factory { PostLikeUseCase(get()) }
    factory { PostDislikeUseCase(get()) }
}