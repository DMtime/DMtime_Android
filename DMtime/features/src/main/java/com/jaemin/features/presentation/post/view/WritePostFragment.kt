package com.jaemin.features.presentation.post.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.features.databinding.FragmentWritePostBinding
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.presentation.post.adapter.PostImageAdapter
import com.jaemin.features.presentation.post.contract.WritePostContract
import com.jaemin.features.util.BitmapManager
import com.jaemin.features.util.FileManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import splitties.toast.toast
import java.io.File


class WritePostFragment : BaseFragment<FragmentWritePostBinding>(), WritePostContract.View {

    private lateinit var postImageAdapter: PostImageAdapter
    private val writePostPresenter: WritePostContract.Presenter by inject { parametersOf(this) }
    private val images = mutableListOf<File>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postImageAdapter = PostImageAdapter()
        binding.postImageRv.adapter = postImageAdapter

        binding.imageAttachBtn.setOnClickListener {
            writePostPresenter.onClickAttachImageButton()
        }

        binding.writePostTv.setOnClickListener {
            writePostPresenter.onClickWritePostButton(
                WrittenPost(isAnonymous(),getImages(),getContents(),getTitle())
            )
        }
    }


    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWritePostBinding {
        return FragmentWritePostBinding.inflate(inflater, container, false)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            if (data != null && data.data != null) {
                images.add(FileManager.getFile(requireActivity(), data.data!!))
                postImageAdapter.addItem(BitmapManager.getBitmap(requireActivity(), data.data!!)!!)
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 200) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 200)
            }
        }
    }

    override fun showSuccessMessage() {
        toast("글을 등록했습니다.")
        requireActivity().supportFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    override fun showErrorMessage() {
        toast("글 등록을 실패했습니다.")

    }

    override fun requestStoragePermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 200)
    }

    override fun isAnonymous(): Boolean {
        return true
    }

    override fun getTitle(): String {
        return binding.postTitleEt.text.toString()
    }

    override fun getContents(): String {
        return binding.postContentEt.text.toString()
    }

    override fun getImages(): List<File> {
        return images
    }


}