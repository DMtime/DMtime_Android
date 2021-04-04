package com.jaemin.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaemin.base.BaseFragment
import com.jaemin.login.databinding.FragmentLoginBinding
import com.jaemin.main.presentation.MainActivity


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val mainIntent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(mainIntent)
            requireActivity().finish()
        }
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container,false)
    }


}