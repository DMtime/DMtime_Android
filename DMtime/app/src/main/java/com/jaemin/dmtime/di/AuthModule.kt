package com.jaemin.dmtime.di

import com.jaemin.features.data.remote.AuthApi
import com.jaemin.features.data.repository.AuthRepositoryImpl
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.usecase.*
import com.jaemin.features.presentation.login.contract.LoginContract
import com.jaemin.features.presentation.login.presenter.LoginPresenter
import com.jaemin.features.presentation.signup.contract.EmailVerificationContract
import com.jaemin.features.presentation.signup.contract.SignUpContract
import com.jaemin.features.presentation.signup.presenter.EmailVerificationPresenter
import com.jaemin.features.presentation.signup.presenter.SignUpPresenter
import com.jaemin.features.presentation.splash.contract.AutoLoginContract
import com.jaemin.features.presentation.splash.presenter.AutoLoginPresenter
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    fun provideAuthApi(retrofit: Retrofit): AuthApi=
            retrofit.create(AuthApi::class.java)
    factory { provideAuthApi(get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(),get())  }
    factory<LoginContract.Presenter> { (view: LoginContract.View) -> LoginPresenter(view, get()) }
    factory<SignUpContract.Presenter> { (view: SignUpContract.View) -> SignUpPresenter(view, get(),get(),get()) }
    factory<EmailVerificationContract.Presenter> { (view: EmailVerificationContract.View) -> EmailVerificationPresenter(view, get()) }
    factory<AutoLoginContract.Presenter> { (view: AutoLoginContract.View) -> AutoLoginPresenter(view, get(),get()) }

    factory { SignUpUseCase(get()) }
    factory { EmailVerificationUseCase(get()) }
    factory { DuplicateUsernameUseCase(get()) }
    factory { DuplicateEmailUseCase(get())}
    factory { LoginUseCase(get()) }
    factory { AutoLoginUseCase(get()) }
    factory { DeleteLoginInfoUseCase(get()) }
}