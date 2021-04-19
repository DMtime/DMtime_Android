package com.jaemin.features.presentation.post.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemImageBinding


class PostImageAdapter(private val imageList : MutableList<Bitmap> = mutableListOf()) : RecyclerView.Adapter<PostImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostImageViewHolder(ItemImageBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

//    fun updateItems(imageList : List<String>){
//        this.imageList.clear()
//        this.imageList.addAll(imageList)
//        notifyDataSetChanged()
//
//    }

    fun addItem(image : Bitmap){
        this.imageList.add(image)
        notifyDataSetChanged()

    }
    override fun getItemCount(): Int =imageList.size
}

class PostImageViewHolder(private val binding : ItemImageBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(image : Bitmap){
        binding.postImg.setImageBitmap(image)
//        Glide.with(binding.root)
//            .load(image)
//            .into(binding.postImg)
    }

}