package com.jaemin.features.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemMainBoardBinding
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.presentation.main.contract.MainContract

class DefaultGalleriesAdapter(
    private val mainPresenter: MainContract.Presenter,
    private val defaultGalleries: MutableList<DefaultGallery> = mutableListOf(),
) :
    RecyclerView.Adapter<DefaultGalleriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultGalleriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBoardBinding.inflate(inflater, parent, false)
        return DefaultGalleriesViewHolder(binding, mainPresenter)
    }

    override fun onBindViewHolder(holder: DefaultGalleriesViewHolder, position: Int) {
        holder.bind(defaultGalleries[position])
    }

    fun updateItems(posts: List<DefaultGallery>) {
        defaultGalleries.clear()
        defaultGalleries.addAll(posts)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = defaultGalleries.size
}

class DefaultGalleriesViewHolder(
    private val binding: ItemMainBoardBinding,
    private val mainPresenter: MainContract.Presenter
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(defaultGallery: DefaultGallery) {
        binding.boardDirImg.setOnClickListener {
            mainPresenter.onClickGallery(defaultGallery.id)
        }
        binding.boardTv.text = defaultGallery.name
        binding.boardRv.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.boardRv.adapter = DefaultGalleryPostsAdapter(defaultGallery.posts, mainPresenter)
        (binding.boardRv.adapter as DefaultGalleryPostsAdapter).notifyDataSetChanged()

    }
}