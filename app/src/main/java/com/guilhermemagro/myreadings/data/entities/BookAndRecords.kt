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
}
