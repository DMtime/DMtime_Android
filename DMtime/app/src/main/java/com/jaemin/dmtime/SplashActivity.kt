package com.jaemin.dmtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jaemin.login.LoginFragment
import com.jaemin.main.MainFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout,LoginFragment())
                .addToBackStack(null)
                .commit()
    }
}