package com.ex.billablehours.core.data.timecard.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ex.billablehours.core.data.timecard.domain.GroupedTimeCardModel

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
@Dao
interface TimeCardDao {

    @Query("SELECT COUNT(*) AS 'totalProject', SUM(rate) AS 'totalRate', project FROM time_card GROUP BY project ")
    fun getAllTimeCardsByGroup(): LiveData<List<GroupedTimeCardModel>>

    @Query("SELECT * FROM time_card WHERE project = :name ORDER BY timeCreated DESC")
    fun getTimeCardsByProjectName(name: String): LiveData<List<DatabaseTimeCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseTimeCard: DatabaseTimeCard)

    @Delete
    fun delete(databaseTimeCard: DatabaseTimeCard)
}