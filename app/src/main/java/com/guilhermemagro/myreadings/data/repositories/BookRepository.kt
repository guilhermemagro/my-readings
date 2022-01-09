package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    // BookAndRecordsDao
    fun getAllBooksAndRecords(): Flow<List<BookAndRecords>>
    fun getBookAndRecordsByBookId(bookId: Int): Flow<BookAndRecords>

    // BookDao
//    fun setCurrentPage(currentPage: Int, bookId: Int)
//    fun getAllBooks(): List<Book>
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun updateBook(book: Book)

    // RecordDao
//    fun updateRecord(record: Record)
//    fun insertRecord(record: Record)
//    fun deleteRecord(record: Record)
}
