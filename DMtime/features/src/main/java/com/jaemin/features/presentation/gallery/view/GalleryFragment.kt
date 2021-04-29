package com.jaemin.features.presentation.gallery.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentGalleryBinding
import com.jaemin.features.domain.entity.PostPreview
import com.jaemin.features.presentation.gallery.adapter.PostsAdapter
import com.jaemin.features.presentation.gallery.contract.GalleryContract
import com.jaemin.features.presentation.post.view.PostFragment
import com.jaemin.features.presentation.post.view.WritePostActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class GalleryFragment : BaseFragment<FragmentGalleryBinding>(), GalleryContract.View {
    private val galleryPresenter: GalleryContract.Presenter by inject { parametersOf(this) }

    private lateinit var postsAdapter: PostsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsAdapter = PostsAdapter(galleryPresenter)
        binding.postsRv.adapter = postsAdapter
        galleryPresenter.onCreate()

//        binding.postsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if ((binding.postsRv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() + 1
//                    == (binding.postsRv.layoutManager as LinearLayoutManager).itemCount) {
//                    galleryPresenter.onLoadMore()
//                }
//
//            }
//        })
//        binding.root.children.iterator().forEach {
//            it.apply {
//                isClickable= false
//            }
//        }
        binding.writePostBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), WritePostActivity::class.java).apply {
                putExtra("galleryId", getGalleryId())
            })

        }
        binding.galleryNameTv.text = getGalleryId()
    }


    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGalleryBinding {
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


    override fun onStart() {
        super.onStart()
        Timber.d("dtonStart")
    }
    override fun onResume() {
        super.onResume()
        Timber.d("dtonResume")
    }
    override fun onPause() {
        super.onPause()
        Timber.d("dtonPause")
    }
    override fun onStop() {
        super.onStop()
        Timber.d("dtonStop ")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("dtonDestroyView")
    }
    override fun onDetach() {
        super.onDetach()
        Timber.d("dtonDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("dtonDestroy")
    }
}