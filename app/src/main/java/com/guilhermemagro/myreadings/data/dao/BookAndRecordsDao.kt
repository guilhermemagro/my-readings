package com.guilhermemagro.myreadings.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import kotlinx.coroutines.flow.Flow

@Dao
interface BookAndRecordsDao {
    @Transaction
    @Query("SELECT * FROM book")
    fun getAll(): Flow<List<BookAndRecords>>

    @Transaction
    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getBookAndRecordsByBookId(bookId: Int): Flow<BookAndRecords>
}
