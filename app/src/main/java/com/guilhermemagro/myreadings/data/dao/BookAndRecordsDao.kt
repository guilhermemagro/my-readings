package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guilhermemagro.myreadings.data.entities.BookAndRecords

@Dao
interface BookAndRecordsDao {
    @Transaction
    @Query("SELECT * FROM book")
    fun getAll(): List<BookAndRecords>

    @Transaction
    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getRecordsByBookId(bookId: Int): BookAndRecords
}