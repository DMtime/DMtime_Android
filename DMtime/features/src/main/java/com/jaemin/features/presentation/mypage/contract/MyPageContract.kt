package com.jaemin.features.presentation.mypage.contract

import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.requestmodel.EditedProfile

interface MyPageContract {
    interface View {
        fun setUserProfile(user: User)

        fun setUserPosts(username: String)

        fun setUserProfileImage()

        fun showProfileEditSubmitButton()

        fun hideProfileEditSubmitButton()

        fun showProfileImageEditButton()

        fun hideProfileImageEditButton()

        fun showUsername()

        fun hideUsername()

        fun requestStoragePermission()

        fun getUsernameEditText(): String

        fun getUserExplainEditText(): String

        fun showUserExplain()

        fun hideUserExplain()

        fun showUsernameEditText()

        fun hideUsernameEditText()

        fun showUserExplainEditText()

        fun hideUserExplainEditText()

        fun showProfileEditButton()

        fun hideProfileEditButton()

    }

    interface Presenter {
        fun onCreate()

        fun onClickProfileEditButton()

        fun onClickProfileEditSubmitButton(user: Pair<User, EditedProfile>)

        fun onClickProfileImageEditButton()

        fun onFinishProfileEdit()


    }
}