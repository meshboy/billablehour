package com.ex.core.data.database

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
interface BaseRepository<T> {
    fun delete(d: T)
    fun insert(vararg d: T)
}