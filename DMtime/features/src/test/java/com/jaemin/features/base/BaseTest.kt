package com.jaemin.features.base

import org.junit.Before
import org.junit.ClassRule
import org.mockito.MockitoAnnotations

open class BaseTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxSchedulerTestRule()
    }

    @Before
    open fun before() {
        MockitoAnnotations.openMocks(this)
    }
}