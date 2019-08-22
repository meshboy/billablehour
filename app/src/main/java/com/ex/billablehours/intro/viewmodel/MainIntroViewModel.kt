package com.ex.billablehours.intro.viewmodel

import com.ex.billablehours.core.data.user.domain.UserModel
import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.intro.view.MainIntroView
import kotlinx.coroutines.*

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-19
 */
class MainIntroViewModel(userRepository: UserRepository)
    : BaseViewModel<MainIntroView>(){

    private val viewJob = Job()

    private val coroutineJob = CoroutineScope(viewJob + Dispatchers.Main)

    val user = userRepository.getOne()

    /**
     * direct user based on her login status. when the login status is false,
     * move to to login screen. when the login status is true, move to home screen
     */
    fun navigateUserBasedOnLoginStatus(userModel: UserModel) {
        coroutineJob.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
                if (userModel.isLoggedIn) {
                    view.navigateToHomeScreen()
                } else {
                    view.navigateToLoginScreen()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewJob.cancel()
    }

}