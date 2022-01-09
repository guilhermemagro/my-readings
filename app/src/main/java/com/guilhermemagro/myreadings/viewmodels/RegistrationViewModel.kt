package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookRepository.insertBook(book)
        }
    }
}