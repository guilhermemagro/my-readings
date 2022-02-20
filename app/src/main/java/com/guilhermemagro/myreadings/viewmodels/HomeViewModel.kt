package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.guilhermemagro.myreadings.data.entities.BookAndRecords
import com.guilhermemagro.myreadings.data.entities.Record
import com.guilhermemagro.myreadings.data.repositories.BookRepository
import com.guilhermemagro.myreadings.utils.DateHelper
import com.guilhermemagro.myreadings.utils.ZERO_PAGES_READ
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    val booksAndRecords: LiveData<List<BookAndRecords>> =
        bookRepository.getAllBooksAndRecords().asLiveData()

    fun increaseTodayCurrentPage(bookId: Int) {
        viewModelScope.launch {
            bookRepository.increaseCurrentPage(bookId)
            bookId.getTodayRecordIfExists()?.let { record ->
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

    private fun Int.getTodayRecordIfExists(): Record? {
        return booksAndRecords.value
            ?.firstOrNull { it.book.id == this }
            ?.getTodayRecordIfExists()
    }

    fun decreaseTodayCurrentPage(bookId: Int) {
        viewModelScope.launch {
            bookId.getTodayRecordIfExists()
                ?.takeIf { record -> record.pagesRead > ZERO_PAGES_READ }
                ?.let { record ->
                    bookRepository.decreaseCurrentPage(bookId)
                    bookRepository.decreaseRecordPages(record.id)
                }
        }
    }
}
