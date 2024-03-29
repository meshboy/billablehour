package com.ex.billablehours.core.di.modules.database

import com.ex.billablehours.core.data.database.BillerHourDatabase
import com.ex.billablehours.core.data.timecard.entities.TimeCardDao
import com.ex.billablehours.core.data.timecard.repository.TimeCardRepository
import com.ex.billablehours.core.data.timecard.repository.TimeCardRepositoryImpl
import com.ex.billablehours.core.data.user.entities.UserDao
import com.ex.billablehours.core.data.user.repository.UserRepository
import com.ex.billablehours.core.data.user.repository.UserRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */

val databseMoudle = Kodein.Module("Database Module") {
//    server one instance of biller hour database by using singleton pattern
    bind<BillerHourDatabase>() with singleton { BillerHourDatabase.getDatabase(instance()) }

//    user
    bind<UserDao>() with singleton { instance<BillerHourDatabase>().userDao }
    bind<UserRepository>() with singleton { UserRepositoryImpl(instance()) }

//    timecard
    bind<TimeCardDao>() with singleton { instance<BillerHourDatabase>().timeCardDao }
    bind<TimeCardRepository>() with singleton { TimeCardRepositoryImpl(instance()) }
}