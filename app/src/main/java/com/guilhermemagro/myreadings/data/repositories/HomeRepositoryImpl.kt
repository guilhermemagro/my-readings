package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.dao.BookAndRecordsDao
import com.guilhermemagro.myreadings.data.dao.BookDao
import com.guilhermemagro.myreadings.data.dao.RecordDao
import com.guilhermemagro.myreadings.data.entities.Book
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val recordDao: RecordDao,
    private val bookAndRecordsDao: BookAndRecordsDao
) : HomeRepository {

    override fun getAllBooksAndRecords() = bookAndRecordsDao.getAll()

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
