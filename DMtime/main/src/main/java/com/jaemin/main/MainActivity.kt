package com.jaemin.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.jaemin.main.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toggle =ActionBarDrawerToggle(this, binding.mainDrawerLayout, binding.mainToolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        binding.mainDrawerLayout.addDrawerListener(toggle)
        binding.mainDrawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener{
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerOpened(drawerView: View) {}

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                binding.mainNestedLayout.translationX = slideOffset * binding.mainNestedLayout.width/2
            }

            override fun onDrawerStateChanged(newState: Int) {}
        })

    }

}