package com.jaemin.features.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.features.domain.repository.GalleryRepository
import com.jaemin.features.domain.requestmodel.AddGallery
import io.reactivex.rxjava3.core.Single

class AddGalleryUseCase(private val galleryRepository: GalleryRepository) : UseCase<AddGallery, Unit>(){
    override fun build(data: AddGallery): Single<Unit> =
        galleryRepository.addGallery(data).toSingleDefault(Unit)

}