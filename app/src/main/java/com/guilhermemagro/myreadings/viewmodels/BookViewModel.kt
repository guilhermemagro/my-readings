package com.guilhermemagro.myreadings.viewmodels

import androidx.lifecycle.ViewModel
import com.guilhermemagro.myreadings.data.repositories.BookRepository

class BookViewModel(
    private val bookRepository: BookRepository
): ViewModel() {

}
