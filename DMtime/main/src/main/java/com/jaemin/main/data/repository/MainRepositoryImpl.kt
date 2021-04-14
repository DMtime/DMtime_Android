package com.jaemin.main.data.repository

import com.jaemin.gallery.data.dto.response.toEntity
import com.jaemin.main.data.dto.response.GalleryResponse
import com.jaemin.main.data.remote.MainApi
import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.repository.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainRepositoryImpl(private val mainApi: MainApi) : MainRepository {

    override fun getDefaultGalleries(): Single<List<DefaultGallery>> {


        val galleriesSingle : Single<List<GalleryResponse>> = mainApi.getDefaultGalleries(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

        val defaultGallery : Single<List<DefaultGallery>>  =Single.zip(galleriesSingle, galleriesSingle.flatMap {
            (Observable.fromIterable(it).flatMap { gallery->
                mainApi.getGalleryPosts(4, 1, gallery.id).toObservable()
            }).toList()

        },{gallery, posts->
            val defaultGalleries = mutableListOf<DefaultGallery>()
            for (i in gallery.indices){
                defaultGalleries.add(DefaultGallery(gallery[i].id, gallery[i].name, posts[i].posts.map { it.toEntity() }))
            }
            defaultGalleries
        })
        return defaultGallery
    }
//    fun getGalleries()
//
//    fun getPostsByGalleryId
}