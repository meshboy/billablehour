package com.ex.billablehours.main.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ex.billablehours.R
import com.ex.billablehours.core.mvvm.BaseActivity
import com.ex.billablehours.databinding.ActivityMainBinding
import com.ex.billablehours.main.view.MainContainerView

class MainActivity : BaseActivity<MainContainerView>(), MainContainerView {

    override fun createView(): MainContainerView = this

    lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        set up navigation controller to hold toolbar and current fragment
        navController = this.findNavController(R.id.fragmentNavHost)
        NavigationUI.setupWithNavController(binding.toolbar, navController)
    }

    override fun showError(error: String?) {

    }
}
