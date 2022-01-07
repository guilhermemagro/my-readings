package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.dao.BookAndRecordsDao
import com.guilhermemagro.myreadings.data.dao.BookDao
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class EditRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val bookAndRecordsDao: BookAndRecordsDao
) : EditRepository {

    override fun getBookAndRecordsByBookId(bookId: Int): Flow<BookAndRecords> {
        return bookAndRecordsDao.getBookAndRecordsByBookId(bookId)
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.delete(book)
    }

    override suspend fun updateBook(book: Book) {
        bookDao.update(book)
    }
}