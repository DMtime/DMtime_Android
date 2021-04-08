package com.jaemin.main.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaemin.base.BaseFragment
import com.jaemin.main.R
import com.jaemin.main.data.dto.response.DefaultGalleryResponse
import com.jaemin.main.databinding.FragmentMainBinding
import com.jaemin.main.domain.entity.DefaultGallery
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MainFragment : BaseFragment<FragmentMainBinding>(), MainContract.View {

    private val presenter: MainPresenter by inject { parametersOf(this)  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }
    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container,false)
    }

    override fun setDefaultGalleries(defaultGalleries: List<DefaultGallery>) {
        binding.defaultGalleriesRv.adapter = DefaultGalleriesAdapter(defaultGalleries)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }
}