package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.dao.BookAndRecordsDao
import com.guilhermemagro.myreadings.data.dao.BookDao
import com.guilhermemagro.myreadings.data.dao.RecordDao
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val recordDao: RecordDao,
    private val bookAndRecordsDao: BookAndRecordsDao
) : BookRepository {

    override fun getAllBooksAndRecords() = bookAndRecordsDao.getAll()

    override fun getBookAndRecordsByBookId(bookId: Int): Flow<BookAndRecords> {
        return bookAndRecordsDao.getBookAndRecordsByBookId(bookId)
    }

    override suspend fun insertBook(book: Book) {
        bookDao.insert(book)
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.delete(book)
    }

    override suspend fun updateBook(book: Book) {
        bookDao.update(book)
    }

    override suspend fun increaseCurrentPage(bookId: Int) {
        bookDao.incrementCurrentPage(bookId)
    }

    override suspend fun decreaseCurrentPage(bookId: Int) {
        bookDao.decreaseCurrentPage(bookId)
    }

    override suspend fun increaseRecordPages(recordId: Int) {
        recordDao.increaseRecordPages(recordId)
    }

    override suspend fun decreaseRecordPages(recordId: Int) {
        recordDao.decreaseRecordPages(recordId)
    }

    override suspend fun insertRecord(record: Record) {
        recordDao.insert(record)
    }
}
