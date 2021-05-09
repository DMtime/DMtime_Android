package com.jaemin.features.presentation.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jaemin.features.databinding.ItemImageDetailBinding

class PostImageViewPagerAdapter (private val imageList : ArrayList<String> = arrayListOf()) : RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(ItemImageDetailBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    fun updateItems(imageList : List<String>){
        this.imageList.clear()
        this.imageList.addAll(imageList)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int =imageList.size
}
class ImageViewHolder(private val binding : ItemImageDetailBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(image : String){
        Glide.with(binding.root)
            .load("https://dmtimebucket.s3.ap-northeast-2.amazonaws.com/images/$image")
            .transition(DrawableTransitionOptions.withCrossFade())
            .override(300,400)
            .into(binding.postImg)
    }

}