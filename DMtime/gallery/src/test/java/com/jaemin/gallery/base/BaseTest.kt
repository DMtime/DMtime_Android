package com.jaemin.gallery.base

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
    fun before() {
        MockitoAnnotations.openMocks(this)
    }
}