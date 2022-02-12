package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import com.guilhermemagro.myreadings.utils.DateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {

    val booksAndRecords: LiveData<List<BookAndRecords>> =
        bookRepository.getAllBooksAndRecords().asLiveData()

    fun increaseCurrentPage(bookId: Int) {
        viewModelScope.launch {
            bookRepository.increaseCurrentPage(bookId)
            bookId.getTodayRecordIfExist()?.let { record ->
                bookRepository.increaseRecordPages(record.id)
            } ?: run {
                bookRepository.insertRecord(
                    Record(
                        bookId = bookId,
                        date = DateHelper.getLocalDate()
                    )
                )
            }
        }
    }

    private fun Int.getTodayRecordIfExist() : Record? {
        return booksAndRecords.value
            ?.flatMap { bookAndRecords -> bookAndRecords.records }
            ?.firstOrNull { record ->
                record.bookId == this && record.date == DateHelper.getLocalDate()
            }
    }

    fun decreaseCurrentPage(bookId: Int) {
        viewModelScope.launch {
            bookRepository.decreaseCurrentPage(bookId)
        }
    }
}
