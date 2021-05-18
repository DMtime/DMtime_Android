package com.jaemin.features.domain.usecase

import com.jaemin.features.domain.repository.AuthRepository

class DeleteLoginInfoUseCase(private val authRepository: AuthRepository) {
    fun execute() : Boolean{
        return authRepository.deleteLoginInfo()
    }
}