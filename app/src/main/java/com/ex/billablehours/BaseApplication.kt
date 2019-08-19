package com.ex.billablehours

import android.app.Application
import com.ex.billablehours.core.di.modules.database.databseMoudle
import com.ex.billablehours.core.di.modules.views.mainModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
class BaseApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))
        import(databseMoudle)
        import(mainModule)
    }

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    /**
     * setup timber for
     */
    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }

}