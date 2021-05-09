package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.UserApi
import com.jaemin.features.data.repository.UserRepositoryImpl
import com.jaemin.features.domain.repository.UserRepository
import com.jaemin.features.domain.usecase.ChangeUserInfoUseCase
import com.jaemin.features.domain.usecase.GetMyInfoUseCase
import com.jaemin.features.domain.usecase.UserPostsUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val userModule = module {
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)
    factory { provideUserApi(get()) }

    factory { ChangeUserInfoUseCase(get()) }

    factory<UserRepository> { UserRepositoryImpl(get()) }


}