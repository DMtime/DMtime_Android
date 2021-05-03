package com.jaemin.features.data.dto.request

import com.jaemin.features.domain.requestmodel.LoginInfo

data class LoginRequest(val email : String, val password : String)

fun LoginInfo.toData() =
        LoginRequest(this.email, this.password)
