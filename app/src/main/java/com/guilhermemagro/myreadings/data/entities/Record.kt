package com.guilhermemagro.myreadings.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Book::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("book_id")
    )]
)
data class Record (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(index = true, name = "book_id") val bookId: Int,
    val date: String,
    @ColumnInfo(name = "pages_read") val pagesRead: Int = 1,
)
