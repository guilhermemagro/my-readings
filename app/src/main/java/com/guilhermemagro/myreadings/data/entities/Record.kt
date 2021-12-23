package com.guilhermemagro.myreadings.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Book::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("bookId")
    )]
)
data class Record (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val bookId: Int,
    val date: String,
    @ColumnInfo(name = "pages_read") val pagesRead: Int,
)
