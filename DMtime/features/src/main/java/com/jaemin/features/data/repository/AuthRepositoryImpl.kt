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
import timber.log.Timber

class AuthRepositoryImpl(private val authApi : AuthApi, private val sharedPreferencesManager: SharedPreferencesManager) : AuthRepository {
    override fun login(loginInfo: Pair<Boolean,LoginInfo>): Single<Token> =
            authApi.login(loginInfo.second.toData()).map {
                sharedPreferencesManager.saveToken(it.accessToken)
                Timber.d(loginInfo.first.toString())
                sharedPreferencesManager.saveAutoLoginState(loginInfo.first)
                it.toModel()
            }

    override fun deleteLoginInfo() : Boolean{
        sharedPreferencesManager.clearToken()
        sharedPreferencesManager.clearAutoLoginState()
        if (!sharedPreferencesManager.getAutoLoginState() && sharedPreferencesManager.getToken()=="empty")
            return true
        return false
    }

    override fun autoLogin() : Boolean{
        if (sharedPreferencesManager.getAutoLoginState()) {
            Timber.d(sharedPreferencesManager.getToken())
            return true
        }
        return false
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