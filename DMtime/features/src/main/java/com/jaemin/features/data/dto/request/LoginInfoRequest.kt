package com.jaemin.features.data.dto.request

import com.jaemin.features.domain.requestmodel.LoginInfo

data class LoginInfoRequest(val email : String, val password : String)

fun LoginInfo.toData() =
        LoginInfoRequest(this.email, this.password)
