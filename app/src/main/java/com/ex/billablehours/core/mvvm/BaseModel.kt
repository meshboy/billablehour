package com.ex.core.mvvm

import androidx.lifecycle.ViewModel

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
abstract class BaseViewModel<V : BaseView> : ViewModel() {

    open lateinit var view: V
}