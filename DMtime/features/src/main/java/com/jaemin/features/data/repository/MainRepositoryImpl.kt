package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.response.GalleryResponse
import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.MainApi
import com.jaemin.features.data.remote.UserApi
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainRepositoryImpl(private val mainApi: MainApi , private val userApi: UserApi) : MainRepository {

    override fun getDefaultGalleries(): Single<List<DefaultGallery>> {


        val galleriesSingle: Single<List<GalleryResponse>> = mainApi.getDefaultGalleries(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

        val defaultGallery: Single<List<DefaultGallery>> = Single.zip(
            galleriesSingle,
            galleriesSingle.flatMap {
                (Observable.fromIterable(it).flatMap
                { gallery ->
                    mainApi.getGalleryPosts(4, 1, gallery.galleryId).map { posts ->
                        Pair(gallery.galleryId, posts)
                    }.toObservable()
                }).toList()

            },
            { gallery, posts ->
                val defaultGalleries = mutableListOf<DefaultGallery>()
                for (i in gallery.indices) {
                    for (j in posts.indices) {
                        if (posts[j].first == gallery[i].galleryId) {
                            defaultGalleries.add(
                                DefaultGallery(
                                    gallery[i].galleryId,
                                    gallery[i].name,
                                    posts[j].second.posts.map { it.toEntity() })
                            )
                        }
                    }
                }
                defaultGalleries
            })
        return defaultGallery
    }

    override fun getMyInfo(): Single<User> =
        userApi.getMyInfo().map {
            it.toEntity()
        }


    override fun getAllGalleries(): Single<List<Gallery>> =
        mainApi.getAllGalleries().map { it.map { gallery ->
            gallery.toEntity()
        } }

}