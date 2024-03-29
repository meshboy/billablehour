package com.ex.billablehours.timecard.viewmodel

import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.timecard.view.GroupedTimeCardView

class GroupedTimeCardViewModel(timeCardRepository: TimeCardRepository)
    : BaseViewModel<GroupedTimeCardView>() {

    val summaries = timeCardRepository.getAllTimeCardsByGroup()

    fun navigateTimeCardCreateScreen() {
        view.navigateToCreateTimeScreen()
    }

    fun navigateToTimeCardScreen(projectName: String){
        view.navigateToTimeCardsScreen(projectName)
    }
}
