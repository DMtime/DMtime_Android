package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.PostsApi
import com.jaemin.features.data.repository.PostsRepositoryImpl
import com.jaemin.features.domain.repository.PostsRepository
import com.jaemin.features.domain.usecase.GetUserLikedPostsUseCase
import com.jaemin.features.domain.usecase.GetUserPostsUseCase
import com.jaemin.features.domain.usecase.GetUserWroteCommentPostsUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val postsModule = module {
    fun providePostsApi(retrofit: Retrofit): PostsApi =
        retrofit.create(PostsApi::class.java)
    factory { providePostsApi(get()) }

    factory<PostsRepository> { PostsRepositoryImpl(get())  }
    factory { GetUserPostsUseCase(get())  }
    factory { GetUserWroteCommentPostsUseCase(get())  }
    factory { GetUserLikedPostsUseCase(get())  }

}