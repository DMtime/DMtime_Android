package com.jaemin.features.domain.repository

import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.responsemodel.Token
import io.reactivex.rxjava3.core.Single

interface AuthRepository {
    fun login(loginInfo: LoginInfo) : Single<Token>
}