package com.jaemin.main.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.main.domain.entity.Post

data class PostResponse(val id: Int,
                        val likes: Int,
                        @SerializedName("number_of_comments")
                        val numberOfComments: Int,
                        @SerializedName("posted_datetime")
                        val postedDatetime: String,
                        val title : String,
                        val uploader : UploaderResponse,
                        val views : Int,
                        @SerializedName("whether_exist_image")
                        val whetherExistImage : Boolean)

fun PostResponse.toEntity() = Post(this.id,this.likes,this.numberOfComments,this.postedDatetime,this.title,this.uploader.toEntity(),this.views,this.whetherExistImage)
