package com.jaemin.features.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentLoginBinding
import com.jaemin.features.presentation.login.contract.LoginContract
import com.jaemin.features.presentation.main.view.MainActivity
import com.jaemin.features.presentation.signup.view.SignUpFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginContract.View{
    private val presenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            presenter.onClickLoginButton()
        }
        binding.signUpTv.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_layout, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun goToMain() {

        requireActivity().startActivity(Intent(requireActivity(),MainActivity()::class.java))
        requireActivity().finish()
    }

    override fun getEmail(): String = binding.emailEt.text.toString()


    override fun getPassword(): String = binding.passwordEt.text.toString()

    override fun showIncorrectEmailMessage() {

        binding.emailEtLayout.error = "학교 이메일이 아닙니다."
    }
}