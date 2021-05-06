package com.jaemin.features.presentation.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemPostsBinding
import com.jaemin.features.domain.entity.PostPreview
import com.jaemin.features.presentation.gallery.contract.GalleryContract
import timber.log.Timber

class PostsAdapter(private val galleryPresenter: GalleryContract.Presenter, private val posts : MutableList<PostPreview> = mutableListOf()) : RecyclerView.Adapter<PostsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostsBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding, galleryPresenter)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        Timber.d(position.toString())
        holder.bind(posts[position])
    }
    fun updateItems(posts : List<PostPreview>){
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }
    fun loadItems(posts : List<PostPreview>){
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
            galleryPresenter.onClickPost(post.id)
        }
        binding.postTitleTv.text = post.title
        binding.postWriterNameTv.text = post.uploader.username
        binding.postDateDetailTv.text = post.postedDatetime
        binding.postRecommendationDetailTv.text = post.numberOfLikes.toString()

    }
}