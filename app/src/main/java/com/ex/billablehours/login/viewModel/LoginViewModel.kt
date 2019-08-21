package com.ex.billablehours.login.viewModel

import com.ex.billablehours.core.data.user.entities.DatabaseUser
import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.login.view.LoginView
import kotlinx.coroutines.*

class LoginViewModel(val userRepository: UserRepository) : BaseViewModel<LoginView>() {

    private val viewJob = Job()

    private val coroutineJob = CoroutineScope(viewJob + Dispatchers.Main)

    fun login(email: String?, password: String?) {

        if (isParamValid(email, password)) {
            view.showLoading()
            coroutineJob.launch {

                val databaseUser: DatabaseUser? = loginUser(email!!, password!!)

                if (databaseUser != null) {
                    view.hideLoading()
                    updateLoggedUser(databaseUser)
                    view.navigateToMainPage()
                } else {
                    view.hideLoading()
                    view.showError("Invalid email / password")
                }
            }
        }
    }

    /**
     * client validation on user input
     */
    fun isParamValid(email: String?, password: String?): Boolean {
        return when {
            email.isNullOrEmpty() -> {
                view.showError("Please enter a valid email")
                false
            }

            password.isNullOrEmpty() -> {
                view.showError("Please enter a valid password")
                false
            }
            else -> true
        }
    }

    /**
     * check the user input against the database credentials in a IO thread
     * without blocking the main thread
     */
    suspend fun loginUser(email: String, password: String): DatabaseUser? {
        return withContext(Dispatchers.IO) {
            userRepository.getUserByEmailAndPassword(email, password)
        }
    }

    /**
     * update the user logged in status upon successful login
     */
    suspend fun updateLoggedUser(databaseUser: DatabaseUser) {
        withContext(Dispatchers.IO) {
            val updatedUser = databaseUser.copy(isLoggedIn = true)
            userRepository.insert(updatedUser)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewJob.cancel()
    }
}
