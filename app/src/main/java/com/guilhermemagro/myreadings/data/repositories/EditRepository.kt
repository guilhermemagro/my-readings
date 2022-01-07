package com.guilhermemagro.myreadings.data.repositories

import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import kotlinx.coroutines.flow.Flow

interface EditRepository {
    suspend fun deleteBook(book: Book)
    suspend fun updateBook(book: Book)
    fun getBookAndRecordsByBookId(bookId: Int): Flow<BookAndRecords>
}
