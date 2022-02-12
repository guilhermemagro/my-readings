package com.guilhermemagro.myreadings.utils

import java.time.LocalDate

object DateHelper {
    fun getLocalDate(): String {
        return LocalDate.now().toString()
    }
}
