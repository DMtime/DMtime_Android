package com.jaemin.base

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver

abstract class UseCase<Parameter, ReturnValue> {
    private val compositeDisposable = CompositeDisposable()
    abstract fun build(data: Parameter): Single<ReturnValue>

    fun execute(data: Parameter, disposableSingleObserver: DisposableSingleObserver<ReturnValue>) {
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