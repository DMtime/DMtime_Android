package com.jaemin.features.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaemin.features.R
import com.jaemin.features.presentation.login.view.LoginFragment
import com.jaemin.features.presentation.main.view.MainActivity
import com.jaemin.features.presentation.splash.contract.AutoLoginContract
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashActivity : AppCompatActivity() , AutoLoginContract.View{
    private val autoLoginPresenter : AutoLoginContract.Presenter by inject { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        autoLoginPresenter.onCreate()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_layout, LoginFragment())
            .commit()
    }

    override fun goToMain() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
}