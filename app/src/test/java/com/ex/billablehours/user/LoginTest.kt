package com.ex.billablehours.user

import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.login.view.LoginView
import com.ex.billablehours.login.viewModel.LoginViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
@RunWith(MockitoJUnitRunner::class)
class LoginTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var loginView: LoginView

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(userRepository)
        loginViewModel.view = loginView
    }

    @Test
    fun validation_fails_on_empty_email() {
        assertFalse(loginViewModel.isParamValid(email = "", password = "notEmpty"))
    }

    @Test
    fun validation_fails_on_empty_password_input() {
        assertFalse(loginViewModel.isParamValid(email = "notEmpty", password = ""))
    }

    @Test
    fun validation_fails_on_both_empty_email_and_password() {
        assertFalse(loginViewModel.isParamValid(email = "", password = ""))
    }

    @Test
    fun validation_pass_on_valid_email_and_password() {
        assertTrue(loginViewModel.isParamValid(email = "user1@hour.com", password = "1234"))
    }
}