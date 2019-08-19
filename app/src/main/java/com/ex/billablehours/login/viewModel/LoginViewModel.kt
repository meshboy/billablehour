package com.ex.billablehours.login.viewModel

import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.login.view.LoginView

class LoginViewModel (userRepository: UserRepository) : BaseViewModel<LoginView>() {

}
