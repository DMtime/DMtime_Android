package com.jaemin.features.domain.usecase

import timber.log.Timber

class EmailValidateUseCase {
    companion object{
        fun validateEmail(email : String) : Boolean{
            if(email.matches(Regex("^.*@dsm.hs.kr$"))) {
                Timber.d(email)
                return true
            }
            return false
        }
    }
}