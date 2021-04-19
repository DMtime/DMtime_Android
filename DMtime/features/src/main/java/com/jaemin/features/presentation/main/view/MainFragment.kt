package com.jaemin.features.presentation.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentMainBinding
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.presentation.gallery.view.GalleryFragment
import com.jaemin.features.presentation.main.adapter.DefaultGalleriesAdapter
import com.jaemin.features.presentation.main.contract.MainContract
import com.jaemin.features.presentation.post.view.PostFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import splitties.toast.toast


class MainFragment : BaseFragment<FragmentMainBinding>(), MainContract.View {

    private val presenter: MainContract.Presenter by inject { parametersOf(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.defaultGalleriesRv.adapter = DefaultGalleriesAdapter(presenter)
    }

    override fun onResume() {
        presenter.onCreate()
        super.onResume()
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container,false)
    }

    override fun setDefaultGalleries(defaultGalleries: List<DefaultGallery>) {
        if (binding.defaultGalleriesRv.adapter != null && !defaultGalleries.isNullOrEmpty()) {
            (binding.defaultGalleriesRv.adapter as DefaultGalleriesAdapter).updateItems(
                defaultGalleries
            )
        }
    }

    override fun setDefaultGalleriesFailed() {
        toast("글을 불러오지 못했어요")
    }

    override fun moveToPost(postId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_drawer_layout, PostFragment().apply {
                arguments = Bundle().apply {
                    putInt("postId", postId)
                }
            })
            .addToBackStack(null)
            .commit()
    }

    override fun moveToGallery(galleryId: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString("galleryId", galleryId)
                }
            })
            .addToBackStack(null)
            .commit()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}