package com.ex.billablehours.timecard.viewmodel

import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.timecard.view.TimeCardListView

class TimeCardListViewModel(
    val timeCardRepository: TimeCardRepository
) : BaseViewModel<TimeCardListView>() {

    fun navigateToTimeCardCreationScreen(model: TimeCardModel?) {
        view.navigateToTimeCreationScreen(model)
    }

    fun navigateToTimeCardCreationScreen() {
        view.navigateToTimeCreationScreen(null)
    }
}
