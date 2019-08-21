package com.ex.billablehours.core.data.timecard.repository

import androidx.lifecycle.LiveData
import com.ex.billablehours.core.data.database.BaseRepository
import com.ex.billablehours.core.data.timecard.domain.GroupedTimeCardModel
import com.ex.billablehours.core.data.timecard.entities.DatabaseTimeCard

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
interface TimeCardRepository: BaseRepository<DatabaseTimeCard> {
    fun getAllTimeCardsByGroup(): LiveData<List<GroupedTimeCardModel>>
    fun getTimeCardsByProjectName(name: String): LiveData<List<DatabaseTimeCard>>
}