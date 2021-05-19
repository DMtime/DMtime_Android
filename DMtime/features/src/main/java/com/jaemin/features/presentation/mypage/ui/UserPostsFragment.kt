package com.jaemin.features.presentation.mypage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentUserWrotePostsBinding
import com.jaemin.features.domain.entity.PostPreview
import com.jaemin.features.domain.usecase.GetUserLikedPostsUseCase
import com.jaemin.features.domain.usecase.UserPostsUseCase
import com.jaemin.features.presentation.gallery.adapter.PostsAdapter
import com.jaemin.features.presentation.mypage.contract.UserPostsContract
import com.jaemin.features.presentation.mypage.presenter.UserPostsPresenter
import com.jaemin.features.presentation.post.view.PostFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


abstract class UserPostsFragment<Presenter : UserPostsPresenter<UseCase>, UseCase : UserPostsUseCase> :
    BaseFragment<FragmentUserWrotePostsBinding>(), UserPostsContract.View {

    abstract val userPostsPresenter: Presenter
    private lateinit var postsAdapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsAdapter = PostsAdapter(userPostsPresenter)
        binding.postsRv.adapter = postsAdapter
        userPostsPresenter.onCreate()
        binding.root.setOnRefreshListener {
            userPostsPresenter.onRefresh()
            binding.root.isRefreshing = false
        }

        binding.postsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (recyclerView.adapter!!.itemCount != 0) {
                    if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        == recyclerView.adapter!!.itemCount - 1
                    ) {
                        userPostsPresenter.onLoadMore()
                    }
                }

            }
        })
    }


    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserWrotePostsBinding {
        return FragmentUserWrotePostsBinding.inflate(inflater, container, false)
    }

    override fun setPosts(posts: List<PostPreview>) {
        postsAdapter.setItems(posts)
    }

    override fun loadPosts(posts: List<PostPreview>) {
        postsAdapter.loadItems(posts)
    }
    override fun showGetPostsFailedMessage() {
    }

    override fun getUsername(): String {
        return requireArguments().getString("username") ?: ""
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

    override fun showProgressBar() {
    }

    override fun hideProgressBar() {
    }

    override fun hideInitProgressBar() {
        binding.userPostsProgressbar.visibility = View.GONE
    }

    override fun onDestroy() {
        userPostsPresenter.onDestroy()
        super.onDestroy()
    }

}