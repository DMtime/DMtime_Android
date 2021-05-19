package com.jaemin.features.domain.repository

import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.requestmodel.SignUpInfo
import com.jaemin.features.domain.responsemodel.Token
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthRepository {
    fun login(loginInfo: Pair<Boolean,LoginInfo>) : Single<Token>

    fun deleteLoginInfo() : Boolean

    fun autoLogin() : Boolean

    fun isNotDuplicateEmail(email : String) : Single<Boolean>

    fun isNotDuplicateUsername(username: String) : Single<Boolean>

    fun signUp(signUpInfo: SignUpInfo) : Completable

    fun verifyEmail(verificationCode: String) : Completable
}