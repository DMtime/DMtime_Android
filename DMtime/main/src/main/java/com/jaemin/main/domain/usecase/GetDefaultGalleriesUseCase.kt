package com.jaemin.main.domain.usecase

import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.repository.MainRepository
import io.reactivex.rxjava3.core.Single

class GetDefaultGalleriesUseCase(private val mainRepository: MainRepository) {
    fun execute(): Single<List<DefaultGallery>> {
        return mainRepository.getDefaultGalleries()
    }
}