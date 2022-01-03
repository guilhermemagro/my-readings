package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myreadings.data.entities.Book
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    val booksAndRecords: LiveData<List<BookAndRecords>> =
        homeRepository.getAllBooksAndRecords().asLiveData()

    fun insertBook(book: Book) {
        viewModelScope.launch {
            homeRepository.insertBook(book)
        }
    }
}
