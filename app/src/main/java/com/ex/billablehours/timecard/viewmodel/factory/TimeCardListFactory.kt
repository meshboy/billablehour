package com.ex.billablehours.timecard.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.timecard.viewmodel.TimeCardListViewModel

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
class TimeCardListFactory(
    private val timeCardRepository: TimeCardRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeCardListViewModel::class.java)) {
            return TimeCardListViewModel(timeCardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class $modelClass")
    }
}