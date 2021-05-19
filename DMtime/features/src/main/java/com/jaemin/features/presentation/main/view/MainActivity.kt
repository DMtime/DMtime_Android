package com.jaemin.features.presentation.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.jaemin.base.BaseActivity
import com.jaemin.features.R
import com.jaemin.features.databinding.ActivityMainBinding
import com.jaemin.features.domain.entity.Gallery
import com.jaemin.features.domain.entity.User
import com.jaemin.features.presentation.splash.SplashActivity
import com.jaemin.features.presentation.gallery.view.GalleryFragment
import com.jaemin.features.presentation.main.contract.MainContract
import com.jaemin.features.presentation.main.MainOptions
import com.jaemin.features.presentation.mypage.ui.MyPageFragment
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity<ActivityMainBinding>(), MainContract.View,
    NavigationView.OnNavigationItemSelectedListener {

    private val mainPresenter: MainContract.Presenter by inject { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        mainPresenter.onCreate()

    }

    override fun setGalleries(galleries: List<Gallery>) {
        galleries.forEachIndexed { idx, gallery ->
            binding.navView.menu.add(
                R.id.gallery_group,
                idx + 1,
                Menu.NONE,
                gallery.name
            ).titleCondensed = gallery.galleryId
        }
        if (binding.navView.menu.size() < 11) {
            while (binding.navView.menu.size() < 11) {
                binding.navView.menu.add(R.id.gallery_group, Menu.NONE, Menu.NONE, "")
            }
        }
        binding.navView.menu.add(R.id.user_group, MainOptions.ADDGALLERY.id(), Menu.NONE, "게시판 추가")
        binding.navView.menu.add(R.id.user_group, MainOptions.MYPAGE.id(), Menu.NONE, "마이페이지")
        binding.navView.menu.add(R.id.user_group, MainOptions.LOGOUT.id(), Menu.NONE, "로그아웃")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUserInfo(user: User) {
        Glide.with(this)
            .load("https://dmtimebucket.s3.ap-northeast-2.amazonaws.com/images/${user.profileImage}")
            .circleCrop()
            .placeholder(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_profile,
                    applicationContext.theme
                )
            )
            .into(binding.navView.getHeaderView(0).findViewById(R.id.user_profile_img))
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.username_tv).text =
            user.username
    }

    override fun goToLogin() {
        finish()
        startActivity(Intent(this, SplashActivity::class.java))
    }

    override fun goToGallery(galleryId: String) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.main_fragment, GalleryFragment().apply {
                arguments = Bundle().apply {
                    putString("galleryId", galleryId)
                }
            }).commit()    }

    private fun initView() {
        setSupportActionBar(binding.mainToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, MainGalleryFragment())
            .commit()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> {
            }
            MainOptions.ADDGALLERY.id() -> {
                supportFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.main_drawer_layout, AddGalleryFragment()).commit()
            }
            MainOptions.LOGOUT.id() -> {
                Toasty.normal(this,"로그아웃 하셨습니다").show()
                mainPresenter.onClickLogOut()
            }
            MainOptions.MYPAGE.id() -> {
                supportFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.main_drawer_layout, MyPageFragment()).commit()
            }
            else -> {
                mainPresenter.onClickGallery(item.titleCondensed.toString())
            }
        }
        binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}