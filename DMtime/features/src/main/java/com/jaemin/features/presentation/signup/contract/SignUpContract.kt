package com.jaemin.features.presentation.signup.contract

interface SignUpContract {

    interface View{
        fun goToEmailVerification()

        fun getUsername() : String

        fun getEmail() : String

        fun getPassword() : String

        fun enableNextButton()

        fun disableNextButton()

        fun initEmailMessage()

        fun initUsernameMessage()

        fun initPasswordConfirmMessage()

        fun showDuplicateUsernameErrorMessage()

        fun showUsernameWordsErrorMessage()

        fun showAvailableUsernameMessage()

        fun showDuplicateEmailErrorMessage()

        fun showAvailableEmailMessage()

        fun showNotEqualPasswordErrorMessage()

        fun showIncorrectPasswordErrorMessage()

        fun showAvailablePasswordMessage()

        fun showIncorrectEmailErrorMessage()
    }
    interface Presenter{
        fun onClickNextButton()

        fun onClickDuplicateUsernameCheckButton()

        fun onClickDuplicateEmailCheckButton()

        fun onTypingEmailEnd(email : String)

        fun onTypingUsernameEnd(username : String)

        fun onTypingPasswordEnd(password : String)

        fun onTypingPasswordConfirmEnd(passwordConfirm : String)

    }
}