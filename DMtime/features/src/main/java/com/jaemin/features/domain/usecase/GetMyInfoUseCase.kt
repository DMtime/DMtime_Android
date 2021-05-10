package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.repository.MainRepository
import com.jaemin.features.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class GetMyInfoUseCase(private val mainRepository: MainRepository) : UseCase<Unit, User>(){
    override fun build(data: Unit): Single<User> =
        mainRepository.getMyInfo()
}