package com.ex.billablehours.timecard.viewmodel

import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.core.data.timecard.domain.asDatabaseModel
import com.ex.billablehours.core.data.timecard.entities.DatabaseTimeCard
import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.core.data.user.domain.UserModel
import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.core.mvvm.BaseViewModel
import com.ex.billablehours.timecard.view.TimeCardView
import kotlinx.coroutines.*

class TimeCardViewModel(
    userRepository: UserRepository,
    val timeCardRepository: TimeCardRepository
) : BaseViewModel<TimeCardView>() {

    private val viewJob = Job()

    private val coroutineJob = CoroutineScope(viewJob + Dispatchers.Main)

    val user = userRepository.getOne()

    var userModel: UserModel? = null


    fun createTimeCard(
        projectName: String?,
        billerRate: String?,
        dateSelected: String?,
        startTime: String?,
        stopTime: String?
    ) {
        if (validateTimeCard(projectName, billerRate, dateSelected, startTime, stopTime)) {

            val timeCreatedModel = TimeCardModel(
                id = null,
                project = projectName?.toLowerCase()?.trim() ?: "",
                rate = billerRate?.toInt() ?: 0,
                date = dateSelected ?: "",
                startTime = startTime ?: "",
                endTime = stopTime ?: "",
                employeeId = userModel?.id ?: 0
            )

            coroutineJob.launch {
                createTimeCard(timeCreatedModel.asDatabaseModel())
                view.navigateToTimeCardListPage()
            }
        }
    }

    fun validateTimeCard(
        projectName: String?,
        billerRate: String?,
        dateSelected: String?,
        startTime: String?,
        stopTime: String?
    ): Boolean {
        return when {
            projectName.isNullOrEmpty() -> {
                view.showError("Please enter a project name")
                false
            }

            billerRate.isNullOrEmpty() -> {
                view.showError("Please enter a valid Biller Rate")
                false
            }

            dateSelected.isNullOrEmpty() -> {
                view.showError("Please select a date")
                false
            }

            startTime.isNullOrEmpty() -> {
                view.showError("Please select start time")
                false
            }

            stopTime.isNullOrEmpty() -> {
                view.showError("Please select stop time")
                false
            }
            else -> true
        }
    }

    suspend fun createTimeCard(databaseTimeCard: DatabaseTimeCard) {
        withContext(Dispatchers.IO) {
            timeCardRepository.insert(databaseTimeCard)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewJob.cancel()
    }
}
