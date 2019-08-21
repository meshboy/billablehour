package com.ex.billablehours.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.core.util.hide
import com.ex.billablehours.core.util.show
import com.ex.billablehours.core.util.snackBar
import com.ex.billablehours.databinding.LoginFragmentBinding
import com.ex.billablehours.login.view.LoginView
import com.ex.billablehours.login.viewModel.LoginViewModel
import com.ex.billablehours.login.viewModel.LoginViewModelFactory
import com.ex.billablehours.main.ui.MainActivity
import org.kodein.di.generic.instance

class LoginFragment : BaseFragment<LoginView>(), LoginView {

    override fun createView(): LoginView = this

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModelFactory: LoginViewModelFactory by instance()

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }


    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.loginButton.setOnClickListener {
            viewModel.login(binding.emailAddressField.text.toString(), binding.passwordField.text.toString())
        }
    }

    override fun navigateToMainPage() {
        val homeIntent = Intent(activity!!, MainActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeIntent)
    }

    override fun showLoading() {
        binding.loginButton.hide(isGone = true)
        binding.progressBar.show()
    }

    override fun hideLoading() {
        binding.loginButton.show()
        binding.progressBar.hide(isGone = true)
    }

    override fun showError(error: String?) {
        error?.let {
            view?.snackBar(error)
        }
    }
}
