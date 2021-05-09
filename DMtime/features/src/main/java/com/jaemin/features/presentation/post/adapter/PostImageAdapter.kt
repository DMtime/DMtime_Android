package com.jaemin.features.presentation.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaemin.features.databinding.ItemImageBinding
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.jaemin.features.presentation.post.contract.PostContract
import com.jaemin.features.presentation.post.presenter.PostPresenter
import com.jaemin.features.presentation.post.view.PostImageFragment

class PostImageAdapter(private val imageList : ArrayList<String> = arrayListOf(), private val postPresenter: PostContract.Presenter) : RecyclerView.Adapter<PostImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostImageViewHolder(ItemImageBinding.inflate(inflater, parent, false), postPresenter)
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    fun updateItems(imageList : List<String>){
        this.imageList.clear()
        this.imageList.addAll(imageList)
        notifyDataSetChanged()

    }
    fun getImages() = imageList

    override fun getItemCount(): Int =imageList.size
}
class PostImageViewHolder(private val binding : ItemImageBinding, private val postPresenter: PostContract.Presenter) : RecyclerView.ViewHolder(binding.root){
    fun bind(image : String){
        Glide.with(binding.root)
            .load("https://dmtimebucket.s3.ap-northeast-2.amazonaws.com/images/$image")
            .transition(withCrossFade())
            .override(300,300)
            .into(binding.postImg)
        binding.postImg.setOnClickListener {
            postPresenter.onClickPostImage(adapterPosition)
        }
    }

}