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
import timber.log.Timber

class TimeCardViewModel(
    userRepository: UserRepository,
    private val timeCardRepository: TimeCardRepository
) : BaseViewModel<TimeCardView>() {

    private val viewJob = Job()

    private val coroutineJob = CoroutineScope(viewJob + Dispatchers.Main)

    val user = userRepository.getOne()

    var userModel: UserModel? = null


    fun createTimeCard(
        id: Long?,
        employeeId: Long?,
        projectName: String?,
        billerRate: String?,
        dateSelected: String?,
        startTime: String?,
        stopTime: String?
    ) {
        if (validateTimeCard(projectName, billerRate, dateSelected, startTime, stopTime)) {

            val timeCreatedModel = TimeCardModel(
                id = id,
                project = projectName?.toLowerCase()?.trim() ?: "",
                rate = billerRate?.toInt() ?: 0,
                date = dateSelected ?: "",
                startTime = startTime ?: "",
                endTime = stopTime ?: "",
//                check if the employeeId is gotteen from update else use the employeeid in session
                employeeId = employeeId ?: userModel?.id ?: 0
            )

            coroutineJob.launch {
                createTimeCard(timeCreatedModel.asDatabaseModel())
                view.navigateToTimeCardListPage(timeCreatedModel.project)
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

    fun delete(id: Long, projectName: String) {
        Timber.d("mesh is heree %s %s", "$id", projectName)
        coroutineJob.launch {
            withContext(Dispatchers.IO) {
                timeCardRepository.deleteById(id)
            }
            view.navigateToTimeCardListPage(projectName)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewJob.cancel()
    }
}
