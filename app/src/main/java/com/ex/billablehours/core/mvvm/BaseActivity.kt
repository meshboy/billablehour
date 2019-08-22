package com.ex.billablehours.core.mvvm

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
abstract class BaseActivity<V : BaseView> : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    abstract fun createView(): V
}