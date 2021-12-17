package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.AppDatabase
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record

class BookRepositoryImpl(
    private val appDatabase: AppDatabase
) : BookRepository {

    private val bookAndRecordsDao = appDatabase.bookAndRecordsDao()
    private val bookDao = appDatabase.bookDao()
    private val recordDao = appDatabase.recordDao()

    override fun getAllBooksAndRecords(): List<BookAndRecords> {
        return bookAndRecordsDao.getAll()
    }

    override fun getRecordsByBookId(bookId: Int): BookAndRecords {
        return bookAndRecordsDao.getRecordsByBookId(bookId)
    }

    override fun setCurrentPage(currentPage: Int, bookId: Int) {
        bookDao.setCurrentPage(currentPage, bookId)
    }

    override fun getAllBooks(): List<Book> {
        return bookDao.getAll()
    }

    override fun insertBook(book: Book) {
        bookDao.insert(book)
    }

    override fun deleteBook(book: Book) {
        bookDao.delete(book)
    }

    override fun updateRecord(record: Record) {
        recordDao.update(record)
    }

    override fun insertRecord(record: Record) {
        recordDao.insert(record)
    }

    override fun deleteRecord(record: Record) {
        recordDao.delete(record)
    }
}
