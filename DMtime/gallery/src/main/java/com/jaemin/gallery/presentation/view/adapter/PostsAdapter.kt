package com.jaemin.gallery.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.gallery.databinding.ItemPostBinding
import com.jaemin.gallery.domain.entity.PostPreview
import com.jaemin.gallery.presentation.presenter.GalleryPresenter

class PostsAdapter(private val defaultPosts : MutableList<PostPreview> = mutableListOf(), private val galleryPresenter: GalleryPresenter) : RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding, galleryPresenter)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(defaultPosts[position])
    }

    override fun getItemCount(): Int = defaultPosts.size
}

class PostsViewHolder(
    private val binding: ItemPostBinding,
    private val galleryPresenter: GalleryPresenter
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostPreview) {
        binding.postLayout.setOnClickListener {
            galleryPresenter.onClickPost(adapterPosition)
        }
        binding.postTitleTv.text = post.title
        binding.postDateTv.text = post.postedDatetime

    }
}