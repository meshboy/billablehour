package com.ex.billablehours.core.data.timecard.domain

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
data class GroupedTimeCardModel constructor(
    val totalProject: Int,
    val totalRate: Int,
    val project: String
)