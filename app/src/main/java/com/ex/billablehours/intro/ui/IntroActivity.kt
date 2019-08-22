package com.ex.billablehours.intro.ui

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ex.billablehours.R
import com.ex.billablehours.core.data.user.entities.asUserModel
import com.ex.billablehours.core.mvvm.BaseActivity
import com.ex.billablehours.databinding.ActivityIntroBinding
import com.ex.billablehours.intro.view.MainIntroView
import com.ex.billablehours.intro.viewmodel.MainIntroViewModel
import com.ex.billablehours.intro.viewmodel.MainIntroViewModelFactory
import com.ex.billablehours.main.ui.MainActivity
import org.kodein.di.generic.instance
import timber.log.Timber

class IntroActivity : BaseActivity<MainIntroView>(), MainIntroView {

    override fun createView(): MainIntroView = this

    private val viewModelFactory: MainIntroViewModelFactory by instance()

    private val viewModel: MainIntroViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainIntroViewModel::class.java)
    }

    lateinit var binding: ActivityIntroBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        navController = findNavController(R.id.mainIntroNavHost)

        binding.viewModel = viewModel
        viewModel.view = createView()

        viewModel.user.observe(this, Observer {
            if(it != null) {
                viewModel.navigateUserBasedOnLoginStatus(it.asUserModel())
            }
        })
    }

    override fun navigateToLoginScreen() {
        if(navController.currentDestination?.id == R.id.splashFragment) {
            navController.navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    override fun navigateToHomeScreen() {
        val homeIntent = Intent(this, MainActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeIntent)
    }

    override fun showError(error: String?) {
        error?.let {
            Timber.d("${IntroActivity::class.java.canonicalName} %s", error)
        }
    }
}
