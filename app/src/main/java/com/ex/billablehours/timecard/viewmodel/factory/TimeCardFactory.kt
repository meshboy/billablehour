package com.ex.billablehours.timecard.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.timecard.viewmodel.TimeCardViewModel

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
class TimeCardFactory(
    private val userRepository: UserRepository,
    private val timeCardRepository: TimeCardRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeCardViewModel::class.java)) {
            return TimeCardViewModel(userRepository, timeCardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class $modelClass")
    }
}