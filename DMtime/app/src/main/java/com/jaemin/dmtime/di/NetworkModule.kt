package com.jaemin.dmtime.di

import com.jaemin.dmtime.AuthorizationInterceptor
import com.jaemin.dmtime.BuildConfig
import com.jaemin.features.data.SharedPreferencesManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    single { SharedPreferencesManager(androidContext()) }

    single {
        AuthorizationInterceptor(get())
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.dmtime.xyz/api/v1/")
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .addInterceptor(get<AuthorizationInterceptor>())
                    .build())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}