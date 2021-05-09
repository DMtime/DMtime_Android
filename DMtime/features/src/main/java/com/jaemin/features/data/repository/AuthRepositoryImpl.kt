package com.jaemin.features.data.repository

import com.jaemin.features.data.SharedPreferencesManager
import com.jaemin.features.data.dto.request.toData
import com.jaemin.features.data.dto.response.toModel
import com.jaemin.features.data.remote.AuthApi
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.requestmodel.SignUpInfo
import com.jaemin.features.domain.responsemodel.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AuthRepositoryImpl(private val authApi : AuthApi, private val sharedPreferencesManager: SharedPreferencesManager) : AuthRepository {
    override fun login(loginInfo: LoginInfo): Single<Token> =
            authApi.login(loginInfo.toData()).map {
                sharedPreferencesManager.saveInfo( it.accessToken,"accessToken")
                it.toModel()
            }

    override fun isNotDuplicateEmail(email: String): Single<Boolean> =
            authApi.isNotDuplicateEmail(email).map { it.usable }

    override fun isNotDuplicateUsername(username: String): Single<Boolean> =
            authApi.isNotDuplicateUsername(username).map { it.usable }

    override fun signUp(signUpInfo: SignUpInfo): Completable =
            authApi.signUp(signUpInfo.toData())

    override fun verifyEmail(verificationCode: String): Completable =
            authApi.verifyEmail(verificationCode)


}