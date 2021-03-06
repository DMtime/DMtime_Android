package com.jaemin.features.presentation.main.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.features.databinding.ItemMainBoardBinding
import com.jaemin.features.domain.entity.DefaultGallery
import com.jaemin.features.presentation.main.contract.MainGalleryContract

class DefaultGalleriesAdapter(
    private val mainGalleryPresenter: MainGalleryContract.Presenter,
    private val defaultGalleries: MutableList<DefaultGallery> = mutableListOf(),
) :
    RecyclerView.Adapter<DefaultGalleriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultGalleriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBoardBinding.inflate(inflater, parent, false)
        return DefaultGalleriesViewHolder(binding, mainGalleryPresenter)
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
    private val mainGalleryPresenter: MainGalleryContract.Presenter
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(defaultGallery: DefaultGallery) {
        binding.boardDirImg.setOnClickListener {
            mainGalleryPresenter.onClickGallery(defaultGallery.id)
        }
        binding.boardTv.text = defaultGallery.name
        binding.boardRv.adapter = DefaultGalleryPostsAdapter(defaultGallery.posts, mainGalleryPresenter)
        binding.boardRv.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                v!!.parent.requestDisallowInterceptTouchEvent(true)
                return false
            }
        })
        (binding.boardRv.adapter as DefaultGalleryPostsAdapter).notifyDataSetChanged()

    }
}