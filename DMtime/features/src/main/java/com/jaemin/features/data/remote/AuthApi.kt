package com.jaemin.features.data.remote

import com.jaemin.features.data.dto.request.LoginRequest
import com.jaemin.features.data.dto.request.SignUpRequest
import com.jaemin.features.data.dto.response.DuplicateResponse
import com.jaemin.features.data.dto.response.TokenResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("token")
    fun login(@Body loginRequest: LoginRequest): Single<TokenResponse>

    @GET("users/email-duplication")
    fun isNotDuplicateEmail(@Query("email") email: String): Single<DuplicateResponse>

    @GET("users/username-duplication")
    fun isNotDuplicateUsername(@Query("username") username: String): Single<DuplicateResponse>

    @POST("email-verification-code")
    fun verifyEmail(@Query("verification-code") verificationCode: String): Completable

    @POST("users")
    fun signUp(@Body signUpRequest: SignUpRequest): Completable
}