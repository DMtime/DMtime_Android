package com.jaemin.features.domain.repository

import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.requestmodel.EditedProfile
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getUserInfo(username : String) : Single<User>

    fun changeUserInfo(currentUser: User, editedUser: EditedProfile) : Completable

}