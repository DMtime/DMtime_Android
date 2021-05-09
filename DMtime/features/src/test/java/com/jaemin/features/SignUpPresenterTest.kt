package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.presentation.signup.contract.SignUpContract
import com.jaemin.features.presentation.signup.presenter.SignUpPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class SignUpPresenterTest : BaseTest(){

    @Mock
    private lateinit var signUpView : SignUpContract.View

    private lateinit var signUpPresenter : SignUpContract.Presenter

    @Mock
    private lateinit var authRepository : AuthRepository
//
//    @Before
//    override fun before() {
//        super.before()
//        signUpPresenter = SignUpPresenter(signUpView, authRepository)
//    }
//    @Test
//    fun `(Given) 중복된 유저이름 (When) 유저이름 입력이 끝났을 때 (Then) 중복 유저이름 메시지 표시`(){
//        `when`(signUpView.getUsername()).thenReturn("test")
//        `when`(authRepository.isNotDuplicateUsername(signUpView.getUsername())).thenReturn(Single.just(false))
//
//        signUpPresenter.onTypingUsernameFinished()
//
//        verify(signUpView).showDuplicateUsernameErrorMessage()
//    }
//
//    @Test
//    fun `(Given) 사용가능 유저이름 (When) 유저이름 입력이 끝났을 때 (Then) 사용 가능 메시지 표시`(){
//        `when`(signUpView.getUsername()).thenReturn("test")
//        `when`(authRepository.isNotDuplicateUsername(signUpView.getUsername())).thenReturn(Single.just(true))
//
//        signUpPresenter.onTypingUsernameFinished()
//
//        verify(signUpView).showAvailableUsernameMessage()
//    }
}