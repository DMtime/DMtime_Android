package com.jaemin.dmtime

import com.jaemin.features.data.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val preference : SharedPreferencesManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization","Bearer ${preference.getToken()}")
                .build()

            return chain.proceed(request)
    }

}