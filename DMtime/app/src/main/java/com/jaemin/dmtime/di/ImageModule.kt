package com.jaemin.dmtime.di

import android.media.Image
import com.jaemin.features.data.remote.GalleryApi
import com.jaemin.features.data.remote.ImageApi
import org.koin.dsl.module
import retrofit2.Retrofit

val imageModule = module {
    fun provideImageApi(retrofit: Retrofit): ImageApi =
        retrofit.create(ImageApi::class.java)

    factory { provideImageApi(get()) }



}