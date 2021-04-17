package com.jaemin.gallery.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.gallery.databinding.ItemPostsBinding
import com.jaemin.gallery.domain.entity.PostPreview
import com.jaemin.gallery.presentation.contract.GalleryContract

class PostsAdapter(private val galleryPresenter: GalleryContract.Presenter,private val posts : MutableList<PostPreview> = mutableListOf()) : RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostsBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding, galleryPresenter)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(posts[position])
    }
    fun updateItems(posts : List<PostPreview>){
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = posts.size
}

class PostsViewHolder(
    private val binding: ItemPostsBinding,
    private val galleryPresenter: GalleryContract.Presenter
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostPreview) {
        binding.postLayout.setOnClickListener {
            galleryPresenter.onClickPost(adapterPosition)
        }
        binding.postTitleTv.text = post.title
        binding.postWriterNameTv.text = post.uploader.username
        binding.postDateDetailTv.text = post.postedDatetime
        binding.postRecommendationDetailTv.text = post.numberOfLikes.toString()

    }
}