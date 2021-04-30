package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.AuthApi
import com.jaemin.features.data.repository.AuthRepositoryImpl
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.usecase.LoginUseCase
import com.jaemin.features.presentation.login.contract.LoginContract
import com.jaemin.features.presentation.login.presenter.LoginPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    fun provideAuthApi(retrofit: Retrofit): AuthApi=
            retrofit.create(AuthApi::class.java)
    factory { provideAuthApi(get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get())  }
    factory<LoginContract.Presenter> { (view: LoginContract.View) -> LoginPresenter(view, get()) }

    factory { LoginUseCase(get()) }
}