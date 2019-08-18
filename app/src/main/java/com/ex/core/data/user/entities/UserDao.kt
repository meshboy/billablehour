package com.ex.core.data.user.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
@Dao
interface UserDao {

//    this operation assumes a single user would be in local session at a time
    @androidx.room.Query("SELECT * FROM User LIMIT 1")
    fun getCurrentUser(): LiveData<DatabaseUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseUser: DatabaseUser)

    @Delete
    fun deleteAll(databaseUser: DatabaseUser)
}