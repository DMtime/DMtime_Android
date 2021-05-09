package com.jaemin.features.presentation.mypage.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentMyPageBinding
import com.jaemin.features.domain.entity.User
import com.jaemin.features.domain.requestmodel.EditedProfile
import com.jaemin.features.presentation.mypage.adapter.UserLogAdapter
import com.jaemin.features.presentation.mypage.contract.MyPageContract
import com.jaemin.features.util.FileCreator
import com.jaemin.features.util.getBitmap
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.File


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(), MyPageContract.View {
    private val myPagePresenter: MyPageContract.Presenter by inject { parametersOf(this) }

    private lateinit var currentUser: User
    private var editedProfileImage: File? = null

    private val getPicture = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == AppCompatActivity.RESULT_OK) {
            if (activityResult.data != null && activityResult.data!!.data != null) {
                editedProfileImage = FileCreator.getFile(
                    requireActivity(),
                    activityResult.data!!.data!!
                )

                binding.profileImg.setImageBitmap(
                    activityResult.data!!.data!!
                        .getBitmap(requireActivity())
                )

            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPagePresenter.onCreate()
        binding.profileEditImg.setOnClickListener {
            myPagePresenter.onClickProfileEditButton()
        }
        binding.profileImageEditImg.setOnClickListener {
            myPagePresenter.onClickProfileImageEditButton()
        }
        binding.profileEditConfirmTv.setOnClickListener {
            myPagePresenter.onClickProfileEditSubmitButton(
                Pair(
                    currentUser,
                    EditedProfile(
                        binding.usernameEditEt.text.toString(),
                        editedProfileImage,
                        binding.userExplainEditEt.text.toString()
                    )
                )
            )
        }
    }


    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding {
        return FragmentMyPageBinding.inflate(inflater, container, false)
    }

    override fun setUserProfile(user: User) {
        currentUser = user
        binding.usernameTv.text = user.username
        binding.userExplainTv.text = user.userExplain ?: "설명이 없습니다."
        binding.usernameEditEt.setText(user.username)
        binding.userExplainEditEt.setText(user.userExplain ?: "")
        Glide.with(requireActivity().applicationContext)
            .load("https://dmtimebucket.s3.ap-northeast-2.amazonaws.com/images/${user.profileImage}")
            .circleCrop()
            .error(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_profile_empty,
                    requireActivity().theme
                )
            )
            .into(binding.profileImg)

    }

    override fun setUserPosts(username: String) {
        binding.myPageVp.adapter = UserLogAdapter(this, username)
        TabLayoutMediator(binding.userLogTabLayout, binding.myPageVp) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "작성글"
                }
                1 -> {
                    tab.text = "댓글단 글"
                }
                2 -> {
                    tab.text = "좋아요한 글"
                }
            }
        }.attach()

    }


    override fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                200
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            getPicture.launch(intent)
        }
    }

    override fun setUserProfileImage() {

    }

    override fun showProfileEditSubmitButton() {
        binding.profileEditConfirmTv.visibility = View.VISIBLE

    }

    override fun hideProfileEditSubmitButton() {
        binding.profileEditConfirmTv.visibility = View.GONE

    }

    override fun showProfileImageEditButton() {
        binding.profileImageEditImg.visibility = View.VISIBLE
    }

    override fun hideProfileImageEditButton() {
        binding.profileImageEditImg.visibility = View.GONE
    }

    override fun showUsername() {
        binding.usernameTv.visibility = View.VISIBLE
    }

    override fun hideUsername() {
        binding.usernameTv.visibility = View.GONE
    }

    override fun getUsernameEditText(): String = binding.usernameEditEt.text.toString()

    override fun getUserExplainEditText(): String = binding.userExplainEditEt.text.toString()

    override fun showUserExplain() {
        binding.userExplainTv.visibility = View.VISIBLE
    }

    override fun hideUserExplain() {
        binding.userExplainTv.visibility = View.GONE
    }

    override fun showUsernameEditText() {
        binding.usernameEditEt.visibility = View.VISIBLE
    }

    override fun hideUsernameEditText() {
        binding.usernameEditEt.visibility = View.GONE

    }

    override fun showUserExplainEditText() {
        binding.userExplainEditEt.visibility = View.VISIBLE

    }

    override fun hideUserExplainEditText() {
        binding.userExplainEditEt.visibility = View.GONE

    }

    override fun showProfileEditButton() {
        binding.profileEditImg.visibility = View.VISIBLE

    }

    override fun hideProfileEditButton() {
        binding.profileEditImg.visibility = View.GONE

    }

}