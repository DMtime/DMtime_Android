package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.responsemodel.Token
import io.reactivex.rxjava3.core.Single

class LoginUseCase(private val authRepository: AuthRepository) : UseCase<LoginInfo, Token>() {
    override fun build(data: LoginInfo): Single<Token> =
        authRepository.login(data)
}