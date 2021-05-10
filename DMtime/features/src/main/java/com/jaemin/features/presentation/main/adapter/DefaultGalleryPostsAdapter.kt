package com.jaemin.features.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemPostBinding
import com.jaemin.features.domain.entity.PostPreview
import com.jaemin.features.presentation.main.contract.MainGalleryContract

class DefaultGalleryPostsAdapter(
    private val defaultPosts: List<PostPreview>,
    private val mainGalleryPresenter: MainGalleryContract.Presenter
) : RecyclerView.Adapter<DefaultGalleryPostsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DefaultGalleryPostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return DefaultGalleryPostsViewHolder(binding, mainGalleryPresenter)
    }

    override fun onBindViewHolder(holder: DefaultGalleryPostsViewHolder, position: Int) {
        holder.bind(defaultPosts[position])
    }

    override fun getItemCount(): Int = defaultPosts.size
}

class DefaultGalleryPostsViewHolder(
    private val binding: ItemPostBinding,
    private val mainGalleryPresenter: MainGalleryContract.Presenter
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostPreview) {
        binding.postLayout.setOnClickListener {
            mainGalleryPresenter.onClickPost(post.id)
        }
        binding.postTitleTv.text = post.title
        binding.postDateTv.text = post.postedDatetime

    }
}