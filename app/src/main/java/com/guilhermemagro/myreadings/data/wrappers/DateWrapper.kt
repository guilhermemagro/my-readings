package com.guilhermemagro.myreadings.data.wrappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DateWrapper private constructor() {

    private lateinit var localDate: LocalDate

    /**
     * @return a date in the ISO-8601 calendar system, such as "2007-12-03"
     */
    fun getDate(): String {
        return localDate.toString()
    }

    /**
     * @return the three initial letters of the day, such as "QUI"
     */
    fun getDay(): String {
        val locale = Locale("pt", "BR")
        return localDate.dayOfWeek.getDisplayName(TextStyle.SHORT, locale).uppercase(locale)
    }

    /**
     * @return the day and month numbers, such as "24/02"
     */
    fun getDayAndMonthDDMM(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM")
        return localDate.format(formatter)
    }

    /**
     * @return a new [DateWrapper] with the result date from subtracting the [daysToSubtract] from
     * the date of this [DateWrapper]
     */
    fun minusDays(daysToSubtract: Long): DateWrapper {
        val localDateResult = localDate.minusDays(daysToSubtract)
        return DateWrapper.from(localDateResult)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as DateWrapper
        if (localDate != other.localDate) return false
        return true
    }

    override fun hashCode(): Int {
        return localDate.hashCode()
    }

    override fun toString(): String {
        return getDate()
    }

    companion object {
        /**
         * @return a [DateWrapper] with the date from now
         */
        fun now(): DateWrapper {
            val dateWrapper = DateWrapper().apply {
                localDate = LocalDate.now()
            }
            return dateWrapper
        }

        /**
         * @param dateAsString date in the ISO-8601 calendar system, such as "2007-12-03"
         * @return a [DateWrapper] with the date from [dateAsString]
         */
        fun from(dateAsString: String): DateWrapper {
            val date = LocalDate.parse(dateAsString)
            val dateWrapper = DateWrapper().apply {
                localDate = date
            }
            return dateWrapper
        }

        /**
         * @return a [DateWrapper] with the date from [localDate]
         */
        fun from(localDate: LocalDate): DateWrapper {
            return DateWrapper().apply {
                this.localDate = localDate
            }
        }
    }
}
