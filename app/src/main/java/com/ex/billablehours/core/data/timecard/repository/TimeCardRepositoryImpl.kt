package com.ex.billablehours.core.data.timecard.repository

import androidx.lifecycle.LiveData
import com.ex.billablehours.core.data.timecard.domain.GroupedTimeCardModel
import com.ex.billablehours.core.data.timecard.entities.DatabaseTimeCard
import com.ex.billablehours.core.data.timecard.entities.TimeCardDao

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
class TimeCardRepositoryImpl(private val timeCardDao: TimeCardDao) : TimeCardRepository {

    override fun getTimeCardsByProjectName(name: String): LiveData<List<DatabaseTimeCard>> {
        return timeCardDao.getTimeCardsByProjectName(name)
    }

    override fun getAllTimeCardsByGroup(): LiveData<List<GroupedTimeCardModel>> {
        return timeCardDao.getAllTimeCardsByGroup()
    }

    override fun delete(d: DatabaseTimeCard) {
        TODO("not implemented")
    }

    override fun deleteById(id: Long) {
        timeCardDao.delete(id)
    }

    override fun insert(vararg d: DatabaseTimeCard) {
        timeCardDao.insertAll(*d)
    }

    override fun getOne(): LiveData<DatabaseTimeCard> {
        TODO("not implemented")
    }

    override fun getAll(): LiveData<List<DatabaseTimeCard>> {
        TODO("not implemented")
    }
}