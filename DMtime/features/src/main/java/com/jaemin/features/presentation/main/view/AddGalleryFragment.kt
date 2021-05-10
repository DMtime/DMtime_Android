package com.jaemin.features.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentAddGalleryBinding
import com.jaemin.features.presentation.main.contract.AddGalleryContract
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class AddGalleryFragment : BaseFragment<FragmentAddGalleryBinding>(), AddGalleryContract.View {

    private val addGalleryPresenter : AddGalleryContract.Presenter by inject { parametersOf(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createGalleryTv.setOnClickListener{
            addGalleryPresenter.onClickAddGalleryButton()
        }
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddGalleryBinding {
        return FragmentAddGalleryBinding.inflate(inflater, container, false)
    }

    override fun getGalleryName(): String = binding.galleryNameEt.text.toString()

    override fun getGalleryId(): String = binding.galleryIdEt.text.toString()

    override fun getGalleryExplain(): String = binding.galleryExplainEt.text.toString()
    override fun goToMain() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    override fun showAddGallerySuccessMessage() {
        Toasty.success(requireActivity(), "게시판을 생성했습니다.", Toast.LENGTH_SHORT, true).show()
    }

}