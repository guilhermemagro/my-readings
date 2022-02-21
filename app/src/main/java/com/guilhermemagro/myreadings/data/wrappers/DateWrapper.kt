package com.guilhermemagro.myreadings.data.wrappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DateWrapper private constructor() {

    private lateinit var localDate: LocalDate

    fun getDate(): String {
        return localDate.toString()
    }

    fun getDay(): String {
        return localDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("pt", "BR"))
    }

    fun getDayAndMonthDDMM(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM")
        return localDate.format(formatter)
    }

    companion object {
        fun new(): DateWrapper {
            val dateWrapper = DateWrapper().apply {
                localDate = LocalDate.now()
            }
            return dateWrapper
        }

        fun newFrom(dateAsString: String): DateWrapper {
            val date = LocalDate.parse(dateAsString)
            val dateWrapper = DateWrapper().apply {
                localDate = date
            }
            return dateWrapper
        }
    }
}
