package com.ex.billablehours.timecard.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ex.billablehours.core.data.timecard.domain.GroupedTimeCardModel

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */
@BindingAdapter("name")
fun TextView.setProjectName(groupedTimeCardModel: GroupedTimeCardModel) {
    text = groupedTimeCardModel.project
}

@BindingAdapter("count")
fun TextView.setTotalTimeCardCount(groupedTimeCardModel: GroupedTimeCardModel){
    text = "${groupedTimeCardModel.totalProject}"
}

@BindingAdapter("rate")
fun TextView.setTotalRate(groupedTimeCardModel: GroupedTimeCardModel) {
    text = "${groupedTimeCardModel.totalRate}"
}