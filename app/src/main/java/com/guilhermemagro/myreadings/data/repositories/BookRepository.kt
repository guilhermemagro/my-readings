package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    // BookAndRecordsDao
    fun getAllBooksAndRecords(): Flow<List<BookAndRecords>>
    fun getBookAndRecordsByBookId(bookId: Int): Flow<BookAndRecords>

    // BookDao
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun updateBook(book: Book)
    suspend fun increaseCurrentPage(bookId: Int)
    suspend fun decreaseCurrentPage(bookId: Int)

    // RecordDao
    suspend fun increaseRecordPages(recordId: Int)
    suspend fun decreaseRecordPages(recordId: Int)
    suspend fun insertRecord(record: Record)
//    fun updateRecord(record: Record)
//    fun deleteRecord(record: Record)
}
