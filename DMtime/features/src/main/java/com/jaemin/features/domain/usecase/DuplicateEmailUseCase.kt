package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single

class DuplicateEmailUseCase(private val authRepository: AuthRepository) : UseCase<String, Boolean>(){
    override fun build(data: String): Single<Boolean> =
            authRepository.isNotDuplicateEmail(data)

}