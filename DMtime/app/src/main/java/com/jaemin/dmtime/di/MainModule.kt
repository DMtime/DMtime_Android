package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.MainApi
import com.jaemin.features.data.repository.MainRepositoryImpl
import com.jaemin.features.domain.repository.MainRepository
import com.jaemin.features.domain.usecase.GetDefaultGalleriesUseCase
import com.jaemin.features.presentation.main.contract.MainContract
import com.jaemin.features.presentation.main.presenter.MainPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val mainModule = module {

    fun provideMainApi(retrofit: Retrofit): MainApi =
        retrofit.create(MainApi::class.java)

    factory { provideMainApi(get()) }
    factory<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get()) }
    factory<MainRepository> { MainRepositoryImpl(get()) }
    factory { GetDefaultGalleriesUseCase(get()) }


}