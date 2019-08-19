package com.ex.billablehours.core.data.user.entities

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
@Dao
interface UserDao {

    //    this operation assumes a single user would be in local session at a time
    @androidx.room.Query("SELECT * FROM user LIMIT 1")
    fun getCurrentUser(): LiveData<DatabaseUser>

    @Query("SELECT * FROM user WHERE email = :email AND password = :password ")
    fun getUserByEmailAndPassword(email: String, password: String): DatabaseUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseUser: DatabaseUser)

    @Delete
    fun deleteAll(databaseUser: DatabaseUser)


    companion object {

        val databaseUser = DatabaseUser(
            email = "user1@hour.com",
//            ignore password encyrption, not to be used this way in production
            password = "1234"
        )
    }
}