package com.jaemin.base

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class UseCase<P, R> {
    private val compositeDisposable = CompositeDisposable()
    abstract fun build(data: P): Single<R>

    fun execute(data: P, disposableSingleObserver: DisposableSingleObserver<R>) {
        val observer = build(data)
            .subscribeOn(SchedulerProvider.io())
            .observeOn(SchedulerProvider.ui())
            .subscribeWith(disposableSingleObserver)
        compositeDisposable.add(observer)

    }

    fun clear() {
        compositeDisposable.clear()
    }
    fun dispose() {
        compositeDisposable.dispose()
    }

}