package com.jaemin.main.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.jaemin.base.BaseActivity
import com.jaemin.main.R
import com.jaemin.main.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.main_fragment)
            }
        }
        binding.navView.menu.getItem(11).setActionView(R.layout.menu_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home-> {
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}