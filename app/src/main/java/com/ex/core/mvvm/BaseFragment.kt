package com.ex.core.mvvm

import androidx.fragment.app.Fragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
abstract class BaseFragment<V : BaseView> : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    abstract fun createView(): V
}