package com.jaemin.dmtime.di

import com.jaemin.main.data.remote.MainApi
import com.jaemin.main.data.repository.MainRepositoryImpl
import com.jaemin.main.domain.repository.MainRepository
import com.jaemin.main.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.main.presentation.MainContract
import com.jaemin.main.presentation.MainPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {

    fun provideMainApi(retrofit: Retrofit): MainApi =
        retrofit.create(MainApi::class.java)

    factory { provideMainApi(get()) }
    factory { (view: MainContract.View) -> MainPresenter(view, get()) }
    factory<MainRepository> { MainRepositoryImpl(get()) }
    factory { GetDefaultGalleriesUseCase(get()) }


}