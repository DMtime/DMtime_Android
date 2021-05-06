package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.repository.MainRepository
import io.reactivex.rxjava3.core.Single

class GetAllGalleriesUseCase(private val mainRepository: MainRepository) : UseCase<Unit, List<Gallery>>() {

    override fun build(data: Unit): Single<List<Gallery>> {
        return mainRepository.getAllGalleries()
    }
}