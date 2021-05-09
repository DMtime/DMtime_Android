package com.jaemin.features.presentation.mypage.presenter

import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.requestmodel.EditedProfile
import com.jaemin.features.domain.usecase.ChangeUserInfoUseCase
import com.jaemin.features.domain.usecase.GetMyInfoUseCase
import com.jaemin.features.presentation.mypage.contract.MyPageContract
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import timber.log.Timber

class MyPagePresenter(
    private val myPageView: MyPageContract.View,
    private val getMyInfoUseCase : GetMyInfoUseCase,
    private val changeUserInfoUseCase: ChangeUserInfoUseCase) : MyPageContract.Presenter {

    override fun onCreate() {
        getMyInfoUseCase.execute(Unit, object : DisposableSingleObserver<User>(){
            override fun onSuccess(user: User) {
                Timber.d(user.toString())
                myPageView.setUserProfile(user)
                myPageView.setUserPosts(user.username)
            }

            override fun onError(e: Throwable) {

            }

        })
    }

    override fun onClickProfileEditButton() {
        myPageView.showUsernameEditText()
        myPageView.showUserExplainEditText()
        myPageView.showProfileEditSubmitButton()
        myPageView.showProfileImageEditButton()
        myPageView.hideProfileEditButton()
        myPageView.hideUsername()
        myPageView.hideUserExplain()
    }
    override fun onFinishProfileEdit() {
        myPageView.hideUsernameEditText()
        myPageView.hideUserExplainEditText()
        myPageView.hideProfileEditSubmitButton()
        myPageView.hideProfileImageEditButton()
        myPageView.showProfileEditButton()
        myPageView.showUsername()
        myPageView.showUserExplain()
    }

    override fun onClickProfileEditSubmitButton(user: Pair<User,EditedProfile>) {
        changeUserInfoUseCase.execute(user, object : DisposableSingleObserver<Unit>(){
            override fun onSuccess(t: Unit) {
                onFinishProfileEdit()
                onCreate()
            }
            override fun onError(e: Throwable) {

            }

        })
    }

    override fun onClickProfileImageEditButton() {
        myPageView.requestStoragePermission()
    }
}