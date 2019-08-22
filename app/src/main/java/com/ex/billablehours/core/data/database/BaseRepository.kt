package com.ex.billablehours.core.data.database

import androidx.lifecycle.LiveData

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
interface BaseRepository<T> {
    fun delete(d: T)
    fun insert(vararg d: T)
    fun getOne(): LiveData<T>
    fun getAll(): LiveData<List<T>>
}