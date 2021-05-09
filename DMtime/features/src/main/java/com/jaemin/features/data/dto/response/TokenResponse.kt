package com.jaemin.features.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.features.domain.responsemodel.Token

data class TokenResponse(
        @SerializedName("access_token")
        val accessToken: String)

fun TokenResponse.toModel() = Token(this.accessToken)
