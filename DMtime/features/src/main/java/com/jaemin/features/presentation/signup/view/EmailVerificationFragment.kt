package com.jaemin.features.presentation.signup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentEmailVerificationBinding
import com.jaemin.features.presentation.login.view.LoginFragment
import com.jaemin.features.presentation.signup.contract.EmailVerificationContract
import es.dmoral.toasty.Toasty
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class EmailVerificationFragment : BaseFragment<FragmentEmailVerificationBinding>(), EmailVerificationContract.View {

    private val emailVerificationPresenter: EmailVerificationContract.Presenter by inject { parametersOf(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emailEt.setText(requireArguments().getString("email"))
        binding.signUpBtn.setOnClickListener {
            emailVerificationPresenter.onSignUpClicked()
        }
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEmailVerificationBinding {
        return FragmentEmailVerificationBinding.inflate(inflater, container, false)
    }

    override fun showIncorrectVerificationCodeMessage() {

    }

    override fun getVerificationCode(): String {
        return binding.verificationNumberEt.text.toString()
    }

    override fun goToLogin() {
        Toasty.success(requireActivity(), "대마타임에 가입하신걸 환영합니다.", Toast.LENGTH_SHORT).show()
        requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_layout, LoginFragment())
                .commit()
    }

}