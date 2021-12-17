package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record

interface BookRepository {
    // BookAndRecordsDao
    fun getAllBooksAndRecords(): List<BookAndRecords>
    fun getRecordsByBookId(bookId: Int): BookAndRecords

    // BookDao
    fun setCurrentPage(currentPage: Int, bookId: Int)
    fun getAllBooks(): List<Book>
    fun insertBook(book: Book)
    fun deleteBook(book: Book)

    // RecordDao
    fun updateRecord(record: Record)
    fun insertRecord(record: Record)
    fun deleteRecord(record: Record)
}
