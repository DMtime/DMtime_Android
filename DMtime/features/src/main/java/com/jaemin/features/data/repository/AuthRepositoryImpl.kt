package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.request.toData
import com.jaemin.features.data.dto.response.toModel
import com.jaemin.features.data.remote.AuthApi
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.responsemodel.Token
import io.reactivex.rxjava3.core.Single

class AuthRepositoryImpl(private val authApi : AuthApi) : AuthRepository {
    override fun login(loginInfo: LoginInfo): Single<Token> =
            authApi.login(loginInfo.toData()).map {
                it.toModel()
            }
}