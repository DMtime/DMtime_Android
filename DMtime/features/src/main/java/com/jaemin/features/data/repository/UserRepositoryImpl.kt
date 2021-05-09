package com.jaemin.features.data.repository

import com.jaemin.features.data.dto.request.UserRequest
import com.jaemin.features.data.dto.response.toEntity
import com.jaemin.features.data.remote.ImageApi
import com.jaemin.features.data.remote.UserApi
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.repository.UserRepository
import com.jaemin.features.domain.requestmodel.EditedProfile
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class UserRepositoryImpl(private val userApi: UserApi, private val imageApi: ImageApi) :
    UserRepository {
    override fun getUserInfo(username: String): Single<User> =
        userApi.getUserInfo(username).map { it.toEntity() }

    override fun changeUserInfo(currentUser: User, editedUser: EditedProfile): Completable {
        if (editedUser.profileImage != null) {

            return Completable.fromSingle(
                imageApi.postImage(
                    MultipartBody.Part.createFormData(
                        "image", editedUser.profileImage.name,
                        editedUser.profileImage.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                    )
                ).flatMap {
                    userApi.changeUserInfo(
                        currentUser.username,
                        UserRequest(editedUser.userExplain, it.image, editedUser.username)
                    ).toSingleDefault(Unit)
                })
        } else {
            return userApi.changeUserInfo(
                currentUser.username,
                UserRequest(editedUser.userExplain, currentUser.profileImage, editedUser.username)
            )
        }

    }


}