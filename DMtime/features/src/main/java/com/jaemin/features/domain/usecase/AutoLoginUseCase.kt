package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.AuthRepository

class AutoLoginUseCase(private val authRepository: AuthRepository) {
    fun execute() : Boolean = authRepository.autoLogin()
    }