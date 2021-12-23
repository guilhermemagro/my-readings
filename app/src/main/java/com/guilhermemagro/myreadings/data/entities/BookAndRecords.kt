package com.guilhermemagro.myreadings.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BookAndRecords(
    @Embedded val book: Book,
    @Relation(parentColumn = "id", entityColumn = "bookId") val records: List<Record>
)
