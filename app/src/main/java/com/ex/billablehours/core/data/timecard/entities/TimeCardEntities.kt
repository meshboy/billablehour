package com.ex.billablehours.core.data.timecard.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import java.util.*

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
@Entity(tableName = "time_card", indices = [Index("employeeId"), Index("project")])
class DatabaseTimeCard constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val employeeId: Long,
    val rate: Int,
    val project: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val timeCreated: Long = Date().time
)

fun List<DatabaseTimeCard>.asDoaminModel(): List<TimeCardModel> {
    return map {
        TimeCardModel(
            id = it.id,
            employeeId = it.employeeId,
            rate = it.rate,
            project = it.project,
            date = it.date,
            startTime = it.startTime,
            endTime = it.endTime
        )
    }
}