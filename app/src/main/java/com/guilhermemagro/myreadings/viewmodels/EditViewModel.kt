package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.repositories.EditRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class EditViewModel @AssistedInject constructor(
    private val editRepository: EditRepository,
    @Assisted private val bookId: Int
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

    @AssistedFactory
    interface Factory {
        fun create(bookId: Int): EditViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            bookId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(bookId) as T
            }
        }
    }
}
