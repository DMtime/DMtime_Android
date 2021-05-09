package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.requestmodel.SignUpInfo
import io.reactivex.rxjava3.core.Single

class SignUpUseCase (private val authRepository: AuthRepository) : UseCase<SignUpInfo, Unit>(){
    var isEmailValidated = false
    var isUsernameValidated = false
    var isPasswordValidated = false
    var isPasswordConfirmValidated = false

    override fun build(data: SignUpInfo): Single<Unit> = authRepository.signUp(data).toSingleDefault(Unit)

    fun isAllValueValidated() : Boolean = isUsernameValidated && isEmailValidated && isPasswordValidated && isPasswordConfirmValidated

}