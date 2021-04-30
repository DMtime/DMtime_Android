package com.jaemin.dmtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaemin.features.presentation.login.view.LoginFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout, LoginFragment())
                .addToBackStack(null)
                .commit()
    }
}