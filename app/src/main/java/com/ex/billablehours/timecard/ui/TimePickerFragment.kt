package com.ex.billablehours.timecard.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-22
 */
@SuppressLint("ValidFragment")
open class TimePickerFragment constructor(private val callback: (String) -> Unit) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()

        val format = "hh:mm"
        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        callback(simpleDateFormat.format(calendar.time))
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hourOfDay = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hourOfDay, minute, false)
    }
}
