package com.ex.billablehours.timecard.view

import com.ex.billablehours.core.data.timecard.domain.TimeCardModel
import com.ex.billablehours.core.mvvm.BaseView

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
interface TimeCardListView : BaseView {
    fun navigateToTimeCreationScreen(timeCardModel: TimeCardModel?)
}