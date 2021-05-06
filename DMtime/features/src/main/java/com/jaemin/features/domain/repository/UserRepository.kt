package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getUserInfo(username : String) : Single<User>
}