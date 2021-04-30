package com.jaemin.features.domain.usecase

import timber.log.Timber

class EmailValidateUseCase {
    companion object{
        fun validateEmail(email : String) : Boolean{
            if(email.matches(Regex("[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@dsm.hs.kr"))) {
                Timber.d("ppap")
                return true
            }
            return false
        }
    }
}