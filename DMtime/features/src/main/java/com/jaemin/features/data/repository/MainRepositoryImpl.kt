package com.jaemin.features.data.repository

import com.jaemin.base.SchedulerProvider
import com.jaemin.features.data.dto.response.GalleryResponse
import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.MainApi
import com.jaemin.features.data.remote.UserApi
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.repository.MainRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class MainRepositoryImpl(private val mainApi: MainApi, private val userApi: UserApi) :
    MainRepository {

    override fun getDefaultGalleries(): Single<List<DefaultGallery>> {


        val galleriesSingle: Single<List<GalleryResponse>> = mainApi.getDefaultGalleries(1)
            .observeOn(SchedulerProvider.ui())
            .subscribeOn(SchedulerProvider.io())

        val defaultGallery: Single<List<DefaultGallery>> =
            galleriesSingle.flatMap { galleries ->
                Observable.fromIterable(galleries).flatMap { gallery ->
                    mainApi.getGalleryPosts(4, 1, gallery.galleryId).map { posts ->
                        Pair(gallery.galleryId, posts)
                    }.toObservable()
                }.toList()
                    .map { defaultPosts ->
                        val defaultGalleries = mutableListOf<DefaultGallery>()
                        for (i in galleries.indices) {
                            for (j in defaultPosts.indices) {
                                if (defaultPosts[j].first == galleries[i].galleryId) {
                                    defaultGalleries.add(
                                        DefaultGallery(
                                            galleries[i].galleryId,
                                            galleries[i].name,
                                            defaultPosts[j].second.posts.map { it.toEntity() })
                                    )
                                }
                            }
                        }
                        return@map defaultGalleries
                    }

            }
        return defaultGallery
    }

    override fun getMyInfo(): Single<User> =
        userApi.getMyInfo().map {
            it.toEntity()
        }


    override fun getAllGalleries(): Single<List<Gallery>> =
        mainApi.getAllGalleries().map {
            it.map { gallery ->
                gallery.toEntity()
            }
        }

}