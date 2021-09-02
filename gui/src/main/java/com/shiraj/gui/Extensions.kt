package com.shiraj.gui

import java.text.SimpleDateFormat
import java.util.*

internal fun getDate(yourDate: String): String {
    val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(yourDate)
    val dayWeekText = SimpleDateFormat("EEEE", Locale.getDefault()).format(date)
    return dayWeekText.toString()
}