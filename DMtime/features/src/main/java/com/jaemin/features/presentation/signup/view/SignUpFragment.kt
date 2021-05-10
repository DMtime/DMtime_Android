package com.jaemin.features.presentation.signup.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.jaemin.base.BaseFragment
import com.jaemin.features.R
import com.jaemin.features.databinding.FragmentSignUpBinding
import com.jaemin.features.presentation.signup.contract.SignUpContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(), SignUpContract.View {

    private val signUpPresenter: SignUpContract.Presenter by inject { parametersOf(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            signUpPresenter.onClickNextButton()
        }
        binding.duplicateEmailCheckBtn.setOnClickListener {
            signUpPresenter.onClickDuplicateEmailCheckButton()
        }
        binding.duplicateUsernameCheckBtn.setOnClickListener {
            signUpPresenter.onClickDuplicateUsernameCheckButton()
        }
        observeEditText(binding.emailEt) { email: String -> signUpPresenter.onTypingEmailEnd(email) }
        observeEditText(binding.usernameEt) { username: String -> signUpPresenter.onTypingUsernameEnd(username) }
        observeEditText(binding.passwordEt) { password: String -> signUpPresenter.onTypingPasswordEnd(password) }
        observeEditText(binding.passwordConfirmEt) { passwordConfirm: String -> signUpPresenter.onTypingPasswordConfirmEnd(passwordConfirm) }
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun goToEmailVerification() {

        requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_layout, EmailVerificationFragment().apply {
                    arguments = Bundle().apply {
                        putString("email", binding.emailEt.text.toString())
                    }
                })
                .addToBackStack(null)
                .hide(this)
                .commit()
    }

    override fun getUsername(): String = binding.usernameEt.text.toString()

    override fun getEmail(): String = binding.emailEt.text.toString()

    override fun getPassword(): String = binding.passwordEt.text.toString()
    override fun enableNextButton() {
        binding.nextBtn.isClickable = true
        binding.nextBtn.isFocusable = true
        binding.nextBtn.background =ResourcesCompat.getDrawable(resources, R.drawable.green_border_filled, requireActivity().applicationContext.theme)
        binding.nextBtn.setTextColor(Color.WHITE)
    }

    override fun disableNextButton() {
        binding.nextBtn.isClickable = false
        binding.nextBtn.isFocusable = false
        binding.nextBtn.background = ResourcesCompat.getDrawable(resources, R.drawable.green_border, requireActivity().applicationContext.theme)
        binding.nextBtn.setTextColor(Color.parseColor("#A6DE57"))
    }

    override fun initEmailMessage() {
        initMessage(binding.emailMessageTv)
    }

    override fun initUsernameMessage() {
        initMessage(binding.usernameMessageTv)
    }

    override fun initPasswordConfirmMessage() {
        initMessage(binding.passwordConfirmMessageTv)
    }

    override fun showDuplicateUsernameErrorMessage() {
        showErrorMessage(binding.usernameMessageTv, "중복된 유저이름 입니다.")
    }

    override fun showUsernameWordsErrorMessage() {
        showErrorMessage(binding.usernameMessageTv, "이름은 2~20자 이내여야 합니다.")
    }

    override fun showAvailableUsernameMessage() {
        showCorrectMessage(binding.usernameMessageTv, "사용가능한 유저이름입니다.")
    }

    override fun showDuplicateEmailErrorMessage() {
        showErrorMessage(binding.emailMessageTv, "중복된 이메일입니다.")
    }

    override fun showAvailableEmailMessage() {
        showCorrectMessage(binding.emailMessageTv, "사용가능한 이메일입니다.")
    }

    override fun showNotEqualPasswordErrorMessage() {
        showErrorMessage(binding.passwordConfirmMessageTv, "입력한 비밀번호와 같지 않습니다.")
    }

    override fun showIncorrectPasswordErrorMessage() {
        showErrorMessage(binding.passwordMessageTv, "비밀번호는 8~36자, 알파벳, 숫자가 포함되어야 합니다.")
    }

    override fun showAvailablePasswordMessage() {
        showCorrectMessage(binding.passwordMessageTv, "사용가능한 비밀번호입니다.")
    }

    override fun showIncorrectEmailErrorMessage() {
        showErrorMessage(binding.emailMessageTv, "학교 이메일이 아닙니다.")
    }

    private fun initMessage(textView: TextView) {
        textView.visibility = View.INVISIBLE
        textView.setTextColor(Color.BLACK)
        textView.text = ""
    }

    private fun showErrorMessage(textView: TextView, message: String) {
        textView.setTextColor(Color.RED)
        textView.visibility = View.VISIBLE
        textView.text = message

    }

    private fun showCorrectMessage(textView: TextView, message: String) {
        textView.setTextColor(Color.parseColor("#A6DE57"))
        textView.visibility = View.VISIBLE
        textView.text = message
    }


    private fun observeEditText(editText: EditText, method: (String) -> Unit) {
        Observable.create { emitter: ObservableEmitter<String>? ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emitter?.onNext(s.toString())
                }

            })
        }
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onNext(text: String) {
                        method(text)
                    }

                    override fun onSubscribe(d: Disposable?) {

                    }

                    override fun onError(e: Throwable?) {
                    }

                    override fun onComplete() {
                    }
                })
    }

}