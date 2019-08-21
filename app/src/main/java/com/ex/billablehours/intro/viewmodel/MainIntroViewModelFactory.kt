package com.ex.billablehours.intro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ex.billablehours.core.data.user.repository.UserRepository

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-19
 */
class MainIntroViewModelFactory(private val userRepository: UserRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainIntroViewModel::class.java)) {
            return MainIntroViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class $modelClass")
    }
}