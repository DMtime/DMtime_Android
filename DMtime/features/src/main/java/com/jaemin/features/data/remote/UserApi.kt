package com.jaemin.features.data.remote

import com.jaemin.features.data.dto.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{username}")
    fun getUserInfo(@Path("username") username : String) : Single<UserResponse>

    @GET("me")
    fun getMyInfo() : Single<UserResponse>

}