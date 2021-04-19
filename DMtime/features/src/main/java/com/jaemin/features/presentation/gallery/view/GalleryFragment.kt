package com.jaemin.features.presentation.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentGalleryBinding
import com.jaemin.features.domain.entity.PostPreview
import com.jaemin.features.presentation.gallery.adapter.PostsAdapter
import com.jaemin.features.presentation.gallery.contract.GalleryContract
import com.jaemin.features.presentation.post.view.PostFragment
import com.jaemin.features.presentation.post.view.WritePostFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class GalleryFragment : BaseFragment<FragmentGalleryBinding>(), GalleryContract.View {
    private val galleryPresenter : GalleryContract.Presenter by inject { parametersOf(this) }

    private lateinit var postsAdapter: PostsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsAdapter = PostsAdapter(galleryPresenter)
        binding.postsRv.adapter = postsAdapter
        binding.writePostBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_drawer_layout, WritePostFragment().apply {
                    arguments = Bundle().apply {
                        putString("galleryId", getGalleryId())
                    }
                }
                )
                .addToBackStack(null)
                .commit()
        }
        binding.galleryNameTv.text = getGalleryId()
        galleryPresenter.onCreate()

    }
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding {
        return FragmentGalleryBinding.inflate(inflater, container, false)
    }

    override fun setPosts(posts: List<PostPreview>) {
        postsAdapter.updateItems(posts)
    }

    override fun showGetPostsFailedMessage() {
    }

    override fun getGalleryId(): String {
        return requireArguments().getString("galleryId") ?: ""
    }

    override fun moveToPost(postId : Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_drawer_layout, PostFragment().apply {
                arguments = Bundle().apply {
                    putInt("postId", postId)
                }
            })
            .addToBackStack(null)
            .commit()

    }

}