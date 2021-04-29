package com.jaemin.features.presentation.post.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.jaemin.base.BaseActivity
import com.jaemin.features.databinding.ActivityWritePostBinding
import com.jaemin.features.domain.entity.WrittenPost
import com.jaemin.features.presentation.post.adapter.PostImageAdapter
import com.jaemin.features.presentation.post.adapter.WritePostImageAdapter
import com.jaemin.features.presentation.post.contract.WritePostContract
import com.jaemin.features.util.BitmapManager
import com.jaemin.features.util.FileManager
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.io.File

class WritePostActivity : BaseActivity<ActivityWritePostBinding>(), WritePostContract.View {
    private val images = mutableListOf<File>()
    private lateinit var writePostImageAdapter: WritePostImageAdapter
    private val getPicture = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            if (activityResult.data != null && activityResult.data!!.data != null) {
                images.add(
                    FileManager.getFile(
                        this@WritePostActivity,
                        activityResult.data!!.data!!
                    )
                )
                writePostImageAdapter.addItem(
                    BitmapManager.getBitmap(
                        this@WritePostActivity,
                        activityResult.data!!.data!!
                    )!!
                )
            }
        }

    }
    private val writePostPresenter: WritePostContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        writePostImageAdapter = WritePostImageAdapter()
        binding.postImageRv.adapter = writePostImageAdapter

        binding.imageAttachBtn.setOnClickListener {
            writePostPresenter.onClickAttachImageButton()
        }

        binding.writePostTv.setOnClickListener {
            writePostPresenter.onClickWritePostButton(
                WrittenPost(isAnonymous(), getImages(), getContents(), getPostTitle())
            )
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == 200) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                val intent = Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
                getPicture.launch(intent)
            }


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun showSuccessMessage() {
        Toasty.success(this, "글을 등록했습니다.", Toast.LENGTH_SHORT, true).show()
        finish()
    }

    override fun showErrorMessage() {
        Toasty.error(this, "글 등록을 실패했습니다.", Toast.LENGTH_SHORT, true).show()

    }

    override fun requestStoragePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            200
        )
    }

    override fun isAnonymous(): Boolean {
        return binding.anonymousCheckBox.isChecked
    }

    override fun getPostTitle(): String {
        return binding.postTitleEt.text.toString()
    }

    override fun getContents(): String {
        return binding.postContentEt.text.toString()
    }

    override fun getImages(): List<File> {
        return images
    }
}