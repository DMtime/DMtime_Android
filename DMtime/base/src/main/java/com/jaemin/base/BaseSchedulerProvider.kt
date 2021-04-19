package com.jaemin.base

import io.reactivex.rxjava3.core.Scheduler

interface BaseSchedulerProvider {

    fun io() : Scheduler

    fun ui() : Scheduler

}