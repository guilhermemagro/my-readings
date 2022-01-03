package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.repositories.EditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EditViewModel @Inject constructor(
    private val editRepository: EditRepository
) : ViewModel() {

    // TODO - Fix it
    suspend fun getBookAndRecordsByBookId(bookId: Int): BookAndRecords? {
        return editRepository.getBookAndRecordsByBookId(bookId)
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            editRepository.deleteBook(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            editRepository.updateBook(book)
        }
    }
}
