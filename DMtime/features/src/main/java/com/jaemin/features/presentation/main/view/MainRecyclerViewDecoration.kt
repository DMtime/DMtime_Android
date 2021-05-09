package com.jaemin.features.presentation.main.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerViewDecoration(private val bottomGap : Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = bottomGap
    }
}