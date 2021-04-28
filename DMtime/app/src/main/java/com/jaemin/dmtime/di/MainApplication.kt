package com.jaemin.dmtime.di

import android.app.Application
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.SocketException

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        RxJavaPlugins.setErrorHandler {
            if (it is UndeliverableException) {

            }
            if (it is HttpException)
                return@setErrorHandler
            if (it is IOException || it is SocketException)
                return@setErrorHandler
            if (it is InterruptedException)
                return@setErrorHandler
            if (it is NullPointerException || it is  IllegalArgumentException){
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), it)
                return@setErrorHandler

            }
            if (it is IllegalStateException){
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), it)
                return@setErrorHandler
            }
        }
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    mainModule,
                    galleryModule,
                    postModule,
                    commentsModule
                )
            )
        }
    }
}