package com.jaemin.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.main.databinding.ItemMainBoardBinding
import com.jaemin.main.domain.entity.DefaultGallery
import com.jaemin.main.domain.entity.Post

class DefaultGalleriesAdapter(private val defaultGalleries : List<DefaultGallery>) : RecyclerView.Adapter<DefaultGalleriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultGalleriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBoardBinding.inflate(inflater, parent, false)
        return DefaultGalleriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DefaultGalleriesViewHolder, position: Int) {
        holder.bind(defaultGalleries[position])
    }
//    fun updateItems(posts: List<Post>){
//        posts.forEach {
//            defaultGalleries.forEach { gallery->
//                gallery.posts.addAll()
//            }
//        }
//
//    }
    override fun getItemCount(): Int = defaultGalleries.size
}
class DefaultGalleriesViewHolder(private val binding: ItemMainBoardBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(defaultGallery: DefaultGallery){
        binding.boardDirImg.setOnClickListener {

        }
        binding.boardTv.text = defaultGallery.name
        binding.boardRv.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.VERTICAL,false)
        binding.boardRv.adapter = DefaultGalleryPostsAdapter(defaultGallery.posts)
        (binding.boardRv.adapter as DefaultGalleryPostsAdapter).notifyDataSetChanged()

    }
}