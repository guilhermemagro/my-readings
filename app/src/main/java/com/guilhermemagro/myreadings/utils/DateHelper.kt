package com.guilhermemagro.myreadings.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun getTodayAsString(): String {
        val cal = Calendar.getInstance()
        val time = cal.time
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("pt","BR"))
        return formatter.format(time)
    }
}
