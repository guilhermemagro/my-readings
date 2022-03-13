package com.guilhermemagro.myreadings.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.guilhermemagro.myreadings.data.wrappers.DateWrapper

data class BookAndRecords(
    @Embedded val book: Book,
    @Relation(parentColumn = "id", entityColumn = "book_id") val records: List<Record>
) {
    fun getTodayRecordIfExists(): Record? {
        return records.firstOrNull { it.date == DateWrapper.now() }
    }

    fun getRecordsFromYesterdayToTheLastSevenDays(): List<Record> {
        val recordsResult: ArrayList<Record> = arrayListOf()
        val dates = getListFromYesterdayToTheLastSevenDays()
        for (date in dates) {
            val record = records.firstOrNull { it.date == date }
                ?: Record(bookId = book.id, date = date, pagesRead = 0)
            recordsResult.add(record)
        }
        return recordsResult.reversed()
    }

    private fun getListFromYesterdayToTheLastSevenDays(): List<DateWrapper> {
        val dates: ArrayList<DateWrapper> = arrayListOf()
        val today = DateWrapper.now()
        for (daysToSubtract in 1..6) {
            dates.add(today.minusDays(daysToSubtract.toLong()))
        }
        return dates
    }
}
