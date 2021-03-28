package com.jaemin.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.jaemin.base.BaseFragment
import com.jaemin.main.databinding.FragmentMainBinding


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var toggle : ActionBarDrawerToggle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        toggle = ActionBarDrawerToggle(requireActivity(), binding.mainDrawerLayout,binding.mainToolbar,R.string.open, R.string.close)
//        binding.mainDrawerLayout.addDrawerListener(toggle)
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container,false)
    }

}