package com.jaemin.features.domain.usecase

class PasswordValidateUseCase {
    companion object{
        fun validatePassword(password : String) : Boolean{
            if(password.matches(Regex("^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z*!@\$%^&(){}\\[\\]:;<>,.?/~_+-=|\\\\]{8,36}\$"))) {
                return true
            }
            return false
        }
    }
}