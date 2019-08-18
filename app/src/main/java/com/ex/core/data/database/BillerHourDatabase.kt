package com.ex.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ex.core.data.user.entities.DatabaseUser
import com.ex.core.data.user.entities.UserDao
import java.util.concurrent.Executors

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
@Database(entities = [DatabaseUser::class], version = 1, exportSchema = false)
abstract class BillerHourDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {

        private lateinit var INSTANCE: BillerHourDatabase
        private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

        fun getDatabase(context: Context): BillerHourDatabase {
            synchronized(BillerHourDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = buildRoomDatabase(context)
                }
            }
            return INSTANCE
        }

        private fun buildRoomDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BillerHourDatabase::class.java,
                "billerhour"
            )
                .addCallback(populateSingleUser(context))
                .build()

        /**
         * prepopulate the user to be in session with the user details
         */
        private fun populateSingleUser(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    ioThread {
                        getDatabase(context = context).userDao.insertAll(UserDao.databaseUser)
                    }
                }
            }
        }

        fun ioThread(f: () -> Unit) {
            IO_EXECUTOR.execute(f)
        }
    }
}