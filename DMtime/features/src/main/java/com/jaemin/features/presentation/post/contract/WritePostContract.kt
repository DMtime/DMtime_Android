package com.jaemin.features.presentation.post.contract

import com.jaemin.features.domain.entity.WrittenPost
import java.io.File

interface WritePostContract {
    interface View {

        fun showSuccessMessage()

        fun showErrorMessage()

        fun requestStoragePermission()

        fun isAnonymous() : Boolean

        fun getPostTitle() : String

        fun getContents() : String

        fun getImages() : List<File>
    }

    interface Presenter {

        fun onClickAttachImageButton()

        fun onClickWritePostButton(writtenPost: Pair<String, WrittenPost>)

    }
}