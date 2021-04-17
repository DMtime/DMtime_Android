package com.jaemin.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.gallery.presentation.view.GalleryFragment
import com.jaemin.gallery.presentation.view.PostFragment
import com.jaemin.main.R
import com.jaemin.main.databinding.FragmentMainBinding
import com.jaemin.main.domain.entity.DefaultGallery
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import splitties.toast.toast


class MainFragment : BaseFragment<FragmentMainBinding>(), MainContract.View {

    private val presenter: MainContract.Presenter by inject { parametersOf(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.defaultGalleriesRv.adapter = DefaultGalleriesAdapter(presenter)
        presenter.onCreate()
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
            .replace(R.id.main_drawer_layout, GalleryFragment().apply {
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
}