package com.jaemin.features.data.dto.request

import com.jaemin.features.domain.requestmodel.SignUpInfo

data class SignUpRequest(val username : String, val password : String, val email : String)

fun SignUpInfo.toData() =
        SignUpRequest(this.username,this.password,this.email)