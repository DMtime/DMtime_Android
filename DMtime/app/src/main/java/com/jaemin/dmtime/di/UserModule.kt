package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val userModule = module {
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)
    factory { provideUserApi(get()) }
}