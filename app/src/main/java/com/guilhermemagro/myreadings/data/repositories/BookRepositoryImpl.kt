package com.guilhermemagro.myreadings.data.repositories

import androidx.lifecycle.LiveData
import com.guilhermemagro.myreadings.data.dao.BookAndRecordsDao
import com.guilhermemagro.myreadings.data.dao.BookDao
import com.guilhermemagro.myreadings.data.dao.RecordDao
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val recordDao: RecordDao,
    private val bookAndRecordsDao: BookAndRecordsDao
) : BookRepository {

    override fun getAllBooksAndRecords(): LiveData<List<BookAndRecords>> {
        return bookAndRecordsDao.getAll()
    }

//    override fun getRecordsByBookId(bookId: Int): BookAndRecords {
//        return bookAndRecordsDao.getRecordsByBookId(bookId)
//    }
//
//    override fun setCurrentPage(currentPage: Int, bookId: Int) {
//        bookDao.setCurrentPage(currentPage, bookId)
//    }
//
//    override fun getAllBooks(): List<Book> {
//        return bookDao.getAll()
//    }

    override suspend fun insertBook(book: Book) {
        bookDao.insert(book)
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.delete(book)
    }

//    override fun updateRecord(record: Record) {
//        recordDao.update(record)
//    }
//
//    override fun insertRecord(record: Record) {
//        recordDao.insert(record)
//    }
//
//    override fun deleteRecord(record: Record) {
//        recordDao.delete(record)
//    }
}
