package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.repository.UserRepository
import com.jaemin.features.domain.requestmodel.EditedProfile
import io.reactivex.rxjava3.core.Single

class ChangeUserInfoUseCase(private val userRepository: UserRepository) : UseCase<Pair<User, EditedProfile>, Unit>() {
    override fun build(data: Pair<User, EditedProfile>): Single<Unit> =
        userRepository.changeUserInfo(data.first, data.second).toSingleDefault(Unit)
}