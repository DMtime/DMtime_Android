package com.jaemin.gallery.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.gallery.databinding.FragmentGalleryBinding


class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentGalleryBinding {
        return FragmentGalleryBinding.inflate(inflater, container, false)
    }
}