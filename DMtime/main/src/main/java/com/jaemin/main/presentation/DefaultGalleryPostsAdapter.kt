package com.jaemin.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.main.databinding.ItemMainBoardBinding
import com.jaemin.main.databinding.ItemPostBinding
import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.entity.Post

class DefaultGalleryPostsAdapter(private val defaultPosts : List<Post>) : RecyclerView.Adapter<DefaultGalleryPostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultGalleryPostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return DefaultGalleryPostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DefaultGalleryPostsViewHolder, position: Int) {
        holder.bind(defaultPosts[position])
    }

    override fun getItemCount(): Int = defaultPosts.size
}
class DefaultGalleryPostsViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post){
        binding.postLayout.setOnClickListener {

        }
        binding.postTitleTv.text = post.title
        binding.postDateTv.text = post.postedDatetime

    }
}