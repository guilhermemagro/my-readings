package com.guilhermemagro.myreadings.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.guilhermemagro.myreadings.utils.DateHelper
import com.guilhermemagro.myreadings.utils.ZERO_PAGES_READ

data class BookAndRecords(
    @Embedded val book: Book,
    @Relation(parentColumn = "id", entityColumn = "book_id") val records: List<Record>
) {
    fun getTodayRecordIfExists(): Record? {
        return records.firstOrNull { it.date == DateHelper.getLocalDate() }
    }
}
