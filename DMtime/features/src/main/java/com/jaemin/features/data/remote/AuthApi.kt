package com.jaemin.features.data.remote

import com.jaemin.features.data.dto.request.LoginInfoRequest
import com.jaemin.features.data.dto.response.TokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("token")
    fun login(@Body loginInfoRequest: LoginInfoRequest): Single<TokenResponse>

}