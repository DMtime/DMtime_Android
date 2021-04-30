package com.jaemin.features

import com.jaemin.features.base.BaseTest
import com.jaemin.features.domain.repository.AuthRepository
import com.jaemin.features.domain.requestmodel.LoginInfo
import com.jaemin.features.domain.responsemodel.Token
import com.jaemin.features.domain.usecase.LoginUseCase
import com.jaemin.features.presentation.login.contract.LoginContract
import com.jaemin.features.presentation.login.presenter.LoginPresenter
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class LoginPresenterTest : BaseTest() {
    @Mock
    private lateinit var loginView : LoginContract.View

    private lateinit var loginPresenter : LoginContract.Presenter

    private lateinit var loginUseCase: LoginUseCase


    @Mock
    private lateinit var authRepository: AuthRepository

    override fun before() {
        super.before()
        loginUseCase = LoginUseCase(authRepository)
        loginPresenter = LoginPresenter(loginView, loginUseCase)
    }

    @Test
    fun `(Given) 학교 이메일 형식이 아닌 이메일(When) 로그인 버튼 클릭 시 (Then) 학교 이메일 형식 오류 메시지 표시`(){
        `when`(loginView.getEmail()).thenReturn("asdf@gmail.com")
        `when`(loginView.getPassword()).thenReturn("12345678!@")

        loginPresenter.onClickLoginButton()

        verify(loginView).showIncorrectEmailMessage()
    }

    @Test
    fun `(Given) 학교 이메일 형식 이메일 (When) 로그인 버튼 클릭 시 (Then) 메인 화면으로 이동`(){
        `when`(loginView.getEmail()).thenReturn("youjmen@dsm.hs.kr")
        `when`(loginView.getPassword()).thenReturn("12345678!@")

        `when`(authRepository.login(LoginInfo("youjmen@dsm.hs.kr","12345678!@"))).thenReturn(Single.just(Token("accesstoken")))

        loginPresenter.onClickLoginButton()

        verify(loginView).goToMain()
    }
}