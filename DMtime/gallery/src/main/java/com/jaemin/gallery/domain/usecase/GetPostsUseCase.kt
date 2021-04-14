package com.jaemin.gallery.domain.usecase

import com.jaemin.base.UseCase
import com.jaemin.gallery.domain.entity.Posts
import com.jaemin.gallery.domain.repository.GalleryRepository
import io.reactivex.rxjava3.core.Single

class GetPostsUseCase(private val galleryRepository: GalleryRepository) : UseCase<Pair<Int, String>, Posts>(){
    override fun build(data: Pair<Int, String>): Single<Posts> =
        galleryRepository.getPosts(4,data.first,data.second)
}