package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.requestmodel.SignUpInfo
import io.reactivex.rxjava3.core.Single

class EmailVerificationUseCase (private val authRepository: AuthRepository) : UseCase<String, Unit>(){
    override fun build(data: String): Single<Unit> = authRepository.verifyEmail(data).toSingleDefault(Unit)
}