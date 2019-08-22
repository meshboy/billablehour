package com.ex.billablehours.core.data.timecard.domain

import android.os.Parcelable
import com.ex.billablehours.core.data.timecard.entities.DatabaseTimeCard
import kotlinx.android.parcel.Parcelize

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
@Parcelize
data class TimeCardModel constructor(
    val id: Long?,
    val rate: Int,
    val project: String,
    val employeeId: Long,
    val startTime: String,
    val endTime: String,
    val date: String
): Parcelable

fun TimeCardModel.asDatabaseModel(): DatabaseTimeCard {
    return DatabaseTimeCard(
        employeeId = employeeId,
        rate = rate,
        project = project,
        date = date,
        startTime = startTime,
        endTime = endTime,
        id = null
    )
}