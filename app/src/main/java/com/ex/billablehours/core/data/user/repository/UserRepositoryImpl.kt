package com.ex.billablehours.core.data.user.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ex.billablehours.core.data.user.entities.DatabaseUser
import com.ex.billablehours.core.data.user.entities.UserDao

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
class UserRepositoryImpl constructor(private val userDao: UserDao) : UserRepository {

    override fun getUserByEmailAndPassword(email: String, password: String): DatabaseUser? {
        return userDao.getUserByEmailAndPassword(email, password)
    }

    override fun delete(d: DatabaseUser) {
        userDao.deleteAll(d)
    }

    override fun insert(vararg d: DatabaseUser) {
        userDao.insertAll(*d)
    }

    override fun getOne(): LiveData<DatabaseUser> {
        return userDao.getCurrentUser()
    }

    override fun getAll(): LiveData<List<DatabaseUser>> {
//        mock an empty live data
        return MutableLiveData()
    }
}