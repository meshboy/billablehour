package com.ex.billablehours.core.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-21
 */


fun View.snackBar(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make(this, message, length)
    snackbar.show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(isGone: Boolean = true) {
    visibility = if (isGone) {
        View.GONE
    } else {
        View.INVISIBLE
    }
}

fun padDigits(digit: Int): String {
    if (digit < 10) {
        return "0$digit"
    }
    return "$digit"
}