package com.ex.billablehours.login.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ex.billablehours.R
import com.ex.billablehours.core.mvvm.BaseFragment
import com.ex.billablehours.databinding.LoginFragmentBinding
import com.ex.billablehours.login.view.LoginView
import com.ex.billablehours.login.viewModel.LoginViewModel
import com.ex.billablehours.login.viewModel.LoginViewModelFactory
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
        savedInstanceState:Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.view = createView()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun showError(error: String?) {

    }
}
