package com.jaemin.features.data.remote

import com.jaemin.features.domain.entity.Image
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {
    @Multipart
    @POST("images")
    fun postImage(@Part image: MultipartBody.Part): Single<Image>

}