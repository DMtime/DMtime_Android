package com.jaemin.gallery.data.dto.response

import com.google.gson.annotations.SerializedName
import com.jaemin.gallery.domain.entity.PostPreview


data class PostPreviewResponse(val id: Int,
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

fun PostPreviewResponse.toEntity() = PostPreview(this.id,this.likes,this.numberOfComments,this.postedDatetime,this.title,this.uploader.toEntity(),this.views,this.whetherExistImage)

