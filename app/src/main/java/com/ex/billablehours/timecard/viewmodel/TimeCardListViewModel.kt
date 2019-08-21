package com.ex.billablehours.timecard.viewmodel

import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.timecard.view.TimeCardView

class TimeCardListViewModel(val userRepository: UserRepository,
                            val timeCardRepository: TimeCardRepository) : BaseViewModel<TimeCardView>() {

}
