package com.ex.billablehours.core.data.timecard.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
@Parcelize
data class TimeCardModel constructor(
    val id: Long,
    val rate: Int,
    val project: String,
    val employeeId: Long,
    val startTime: Long,
    val endTime: Long,
    val date: Long
): Parcelable