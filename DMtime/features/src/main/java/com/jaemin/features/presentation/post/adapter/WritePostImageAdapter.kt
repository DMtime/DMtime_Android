package com.jaemin.features.presentation.post.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemImageBinding


class WritePostImageAdapter(private val imageList : MutableList<Bitmap> = mutableListOf()) : RecyclerView.Adapter<WritePostImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WritePostImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WritePostImageViewHolder(ItemImageBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: WritePostImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    fun addItem(image : Bitmap){
        this.imageList.add(image)
        notifyDataSetChanged()

    }
    override fun getItemCount(): Int =imageList.size
}

class WritePostImageViewHolder(private val binding : ItemImageBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(image : Bitmap){
        binding.postImg.setImageBitmap(image)
    }

}