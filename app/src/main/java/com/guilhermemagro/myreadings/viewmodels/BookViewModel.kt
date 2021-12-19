package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.ViewModel
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    fun insertBook(book: Book) {
        bookRepository.insertBook(book)
    }

    fun getAllBooksAndRecords(): List<BookAndRecords> {
        return bookRepository.getAllBooksAndRecords()
    }
}
