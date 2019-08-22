package com.ex.billablehours.timecard.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.core.util.padDigits

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-22
 */

@BindingAdapter("employeeId")
fun TextView.setEmployeeId(timeCardModel: TimeCardModel) {
    text = padDigits(timeCardModel.employeeId.toInt())
}

@BindingAdapter("projectName")
fun TextView.setProjectName(timeCardModel: TimeCardModel) {
    text = timeCardModel.project.capitalize()
}

@BindingAdapter("startTime")
fun TextView.setStartTime(timeCardModel: TimeCardModel) {
    text = timeCardModel.startTime
}

@BindingAdapter("endTime")
fun TextView.setEndTime(timeCardModel: TimeCardModel) {
    text = timeCardModel.endTime
}

@BindingAdapter("rate")
fun TextView.setRate(timeCardModel: TimeCardModel) {
    text = "${timeCardModel.rate}"
}

@BindingAdapter("date")
fun TextView.setDate(timeCardModel: TimeCardModel) {
    text = timeCardModel.date
}