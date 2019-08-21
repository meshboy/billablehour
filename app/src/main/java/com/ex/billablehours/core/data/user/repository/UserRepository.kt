package com.ex.billablehours.core.data.user.repository

import com.ex.billablehours.core.data.database.BaseRepository
import com.ex.billablehours.core.data.user.entities.DatabaseUser

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
interface UserRepository : BaseRepository<DatabaseUser> {

    fun getUserByEmailAndPassword(email: String, password: String): DatabaseUser?
}